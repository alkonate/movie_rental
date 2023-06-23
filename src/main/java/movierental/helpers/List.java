package movierental.helpers;

import java.util.Objects;
import java.util.function.Function;

import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class List<V> {
    @SuppressWarnings("rawtypes")
    private static List EMPTY_LIST = new EmptyList();
    //sealed
    private List(){}

    public abstract  String toString();
    public abstract  int size();
    public abstract  <R> R match(Supplier<R> f1, Function<List<V>,R> f2);
    public abstract  List<V> reverse();
    public abstract  List<V> concat(List<V> list);
    public abstract  List<V> filter(Predicate<V> f);
    public abstract  <R> R foldLeft(R acc, Function<R, Function<V,R>> f);
    public abstract  <R> R foldRight(R acc, Function<V, Function<R,R>> f);
    public abstract  <R> List<R> map(Function<V,R> f);
    public abstract boolean isEmpty();
    public abstract V head();
    public abstract List<V> tail();


    public List<V> prepend(V v){
        return new Cons<>(v,this);
    }

    public static class EmptyList<V> extends List<V>{
        private EmptyList(){}

        @Override
        public boolean equals(Object obj) {
            return obj instanceof List<?> list && list.isEmpty();
        }

        @Override
        public String toString() {
            return "{END}";
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public <R> R match(Supplier<R> f1, Function<List<V>, R> f2) {
            return f1.get();
        }


        @Override
        public List<V> reverse() {
            return List.of();
        }

        @Override
        public List<V> concat(List<V> list) {
            return list;
        }

        @Override
        public List<V> filter(Predicate<V> f) {
            return List.of();
        }

        @Override
        public <R> R foldLeft(R acc, Function<R, Function<V, R>> f) {
            return acc;
        }

        @Override
        public <R> R foldRight(R acc, Function<V, Function<R, R>> f) {
            return acc;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <R> List<R> map(Function<V, R> f) {
            return EMPTY_LIST;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public V head() {
            throw new IllegalStateException("Empty list has no head");
        }

        @Override
        public List<V> tail() {
            throw new IllegalStateException("Empty list has no tail");
        }
    }

    public static class Cons<V>  extends List<V> {
        private final V head;
        private final List<V> tail;
        private Cons(V head,List<V> tail){
            this.head = Objects.requireNonNull(head);
            this.tail = Objects.requireNonNull(tail);
        }




        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public V head() {
            return head;
        }

        @Override
        public List<V> tail() {
            return tail;
        }

        @Override
        public String toString() {
            return "{%s,END}".formatted(foldLeft(new StringBuilder(),y -> x -> y.isEmpty() ? y.append(x) : y.append(",").append(x)));
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            return obj instanceof List<?> list && list.size() == size() && equals(this, list, true).eval();
        }

        static private <V> TailCall<Boolean> equals(List<V> list,List<?> obj, boolean isEqual){
            return  !isEqual || list.isEmpty()
                    ? TailCall.ret(isEqual)
                    : TailCall.sus(() -> equals(list.tail(),obj.tail(),list.head().equals(obj.head())));
        }

        @Override
        public int size() {
            return foldLeft(0, acc -> y -> acc + 1);
        }

        @Override
        public <R> R match(Supplier<R> f1, Function<List<V>, R> f2) {
            return f2.apply(this);
        }


        @Override
        public List<V> reverse() {
            return foldLeft(List.of(),list -> item -> new Cons<>(item,list));
        }

        @Override
        public List<V> concat(List<V> list) {
            return foldRight(list,head -> tail -> tail.prepend(head));
        }

        @Override
        public List<V> filter(Predicate<V> f) {
            return foldRight(List.of(),head -> tail -> f.test(head) ? new Cons<>(head,tail) : tail );
        }

        @Override
        public <R> List<R> map(Function<V, R> f) {
            return foldRight(List.of(), head -> tail -> new Cons<>(f.apply(head),tail));
        }

        @Override
        public <R> R foldLeft(R acc, Function<R, Function<V, R>> f) {
            return foldLeft(this,acc,f).eval();
        }

        static private <V, R> TailCall<R> foldLeft(List<V> list, R acc, Function<R, Function<V,R>> f) {
            return list.isEmpty()
                    ? TailCall.ret(acc)
                    : TailCall.sus(() -> foldLeft(list.tail(),f.apply(acc).apply(list.head()),f));
        }

        @Override
        public <R> R foldRight(R acc, Function<V, Function<R,R>> f) {
            return foldRight(this.reverse(),acc,f).eval();
        }

        static private <V, R> TailCall<R> foldRight(List<V> list, R acc, Function<V, Function<R,R>> f) {
            return list.isEmpty()
                    ? TailCall.ret(acc)
                    : TailCall.sus(() -> foldRight(list.tail(),f.apply(list.head()).apply(acc),f));
        }



    }

    @SuppressWarnings("unchecked")
    public static <V> List<V> of(){
        return EMPTY_LIST;
    }
    @SafeVarargs
    public static <V> List<V> of(final V ...v){
        List<V> n = new EmptyList<>();
        for (int i = v.length - 1; i >= 0; i--) {
            n = new Cons<>(v[i],n);
        }
        return n;
    }

}
