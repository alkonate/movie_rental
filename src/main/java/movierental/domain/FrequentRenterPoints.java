package movierental.domain;

import java.util.function.Function;

public record FrequentRenterPoints(int point){
    public static FrequentRenterPoints zero() {
        return new FrequentRenterPoints(0);
    }

    public static FrequentRenterPoints of(int point) {
        return new FrequentRenterPoints(point);
    }

    public FrequentRenterPoints inc(){
        return new FrequentRenterPoints(point + 1);
    }

    public FrequentRenterPoints add(FrequentRenterPoints frequentRentPoint) {
        return new FrequentRenterPoints(point + frequentRentPoint.point);
    }

    public String toString() {
        return String.valueOf(point);
    }

    public FrequentRenterPoints when(boolean isTrue, Function<FrequentRenterPoints,FrequentRenterPoints> f) {
        return isTrue
                ? f.apply(this)
                : this;
    }
}
