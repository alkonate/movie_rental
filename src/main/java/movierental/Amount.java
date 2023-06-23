package movierental;

import java.util.function.Function;

public record Amount(double amount){
    static public Amount zero(){
        return new Amount(0);
    }

    public static Amount of(double  amount) {
        return new Amount(amount);
    }

    public String toString() {
        return String.valueOf(amount);
    }

    public Amount add(Amount amount){
        return new Amount(this.amount + amount.amount);
    }

    public Amount when(boolean isTrue,Function<Amount,Amount> f) {
        return isTrue
                ? f.apply(this)
                : this;
    }
}
