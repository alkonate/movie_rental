package movierental;

import java.util.Objects;

import static movierental.RentalAmountCalculator.calRentalAmount;

/**
 * The rental class represents a customer renting a movie.
 */
public class Rental {

    private final Movie _movie;
    private final DaysRented _daysRented;

    public Rental(Movie movie, DaysRented daysRented) {
        _movie = Objects.requireNonNull(movie);
        _daysRented = Objects.requireNonNull(daysRented);
    }

    public int getDaysRented() {
        return _daysRented.getDaysRented();
    }

    public Movie getMovie() {
        return _movie;
    }

    public Amount calculatePrice(){
        return calRentalAmount(_movie.getPriceCode(),_daysRented);
    }

    public FrequentRenterPoints calculateRenterPoints(){
        return FrequentRenterPointsCalculator.calFrequentRenterPoints(_movie.getPriceCode(),_daysRented);
    }
}
//        Rental rental = this;
//        FrequentRenterPoints curr =
//                FrequentRenterPoints.zero()
//                        .incr();
//        // add bonus for a two day new release rental
//        if ((rental.getMovie().getPriceCode().equals(PriceCode.TWO)) && rental.getDaysRented() > 1)
//            curr.incr();
//        return curr;
