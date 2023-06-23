package movierental.domain;

import movierental.helpers.List;
import movierental.helpers.Tuple;

import java.util.Objects;
import java.util.function.Function;

public  class StatementResult {
    public static StatementResult EMPTY = new StatementResult(List.of(),Amount.zero(), FrequentRenterPoints.zero());
    private final List<Tuple<Movie,Amount>> moviesAndPrices;
    private final Amount amount;
    private final FrequentRenterPoints frequentRentPoints;

    public static StatementResult of(List<Tuple<Movie,Amount>> moviesAndPrices, Amount amount, FrequentRenterPoints frequentRentPoint){
        Objects.requireNonNull(moviesAndPrices);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(frequentRentPoint);
        return new StatementResult(moviesAndPrices,amount,frequentRentPoint);
    }
    public static StatementResult of(Tuple<Movie,Amount> moviesAndPrices, Amount amount, FrequentRenterPoints frequentRentPoint){
        Objects.requireNonNull(moviesAndPrices);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(frequentRentPoint);
        return new StatementResult(moviesAndPrices,amount,frequentRentPoint);
    }
    public StatementResult(Tuple<Movie,Amount> moviesAndPrices, Amount amount, FrequentRenterPoints frequentRentPoint) {
        this.moviesAndPrices = List.of(moviesAndPrices);
        this.amount = amount;
        this.frequentRentPoints = frequentRentPoint;
    }
    public StatementResult(List<Tuple<Movie,Amount>> moviesAndPrices, Amount amount, FrequentRenterPoints frequentRentPoint) {
        this.moviesAndPrices = moviesAndPrices;
        this.amount = amount;
        this.frequentRentPoints = frequentRentPoint;
    }
    public StatementResult map(Function<List<Tuple<Movie,Amount>>, Function<Amount, Function<FrequentRenterPoints, StatementResult>>> f1) {
        return f1.apply(moviesAndPrices).apply(amount).apply(frequentRentPoints);
    }
    public <R> R fold(Function<StatementResult, R> f1) {
        return f1.apply(this);
    }

    public Amount getTotalAmount() {
        return amount;
    }

    public FrequentRenterPoints getFrequentRenterPoints() {
        return frequentRentPoints;
    }
    public List<Tuple<Movie,Amount>> getMoviesAndPrices() {
        return moviesAndPrices;
    }
}
