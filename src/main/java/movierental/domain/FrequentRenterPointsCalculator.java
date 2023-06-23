package movierental.domain;

import movierental.domain.Movie.PriceCode;

public class FrequentRenterPointsCalculator {

    public static FrequentRenterPoints calFrequentRenterPoints(PriceCode priceCode, DaysRented daysRented) {
        return FrequentRenterPoints.zero()
                .inc()
                .when(
                        (priceCode.equals(PriceCode.ONE)) && daysRented.isOver(1),
                        FrequentRenterPoints::inc);
    }
}
