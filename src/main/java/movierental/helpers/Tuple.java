package movierental.helpers;


import java.util.Objects;

public class Tuple <A,B>{
    private final A _1;
    private final B _2;

    private Tuple(A a, B b){
        this._1 = Objects.requireNonNull(a);
        this._2 = Objects.requireNonNull(b);
    }

    public static <A,B> Tuple<A,B> of(A a, B b){
        return new Tuple<>(a,b);
    }

    public A get_1() {
        return _1;
    }

    public B get_2() {
        return _2;
    }
}

