package movierental;

import movierental.domain.DaysRented;
import movierental.domain.FrequentRenterPoints;
import movierental.domain.Movie.PriceCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static movierental.domain.FrequentRenterPointsCalculator.calFrequentRenterPoints;

class FrequentRenterPointsCalculatorTest {

    @Test
    void should_get_1_point_when_price_code_is_zero() {
        Assertions.assertEquals(
                FrequentRenterPoints.of(1),
                calFrequentRenterPoints(PriceCode.ZERO, DaysRented.TWO));
    }

    @Test
    void should_get_1_point_when_price_code_1_and_days_rented_1() {
        Assertions.assertEquals(
                FrequentRenterPoints.of(1),
                calFrequentRenterPoints(PriceCode.ONE,DaysRented.ONE));
    }

    @Test
    void should_get_1_point_when_price_code_1_and_days_rented_2() {
        Assertions.assertEquals(
                FrequentRenterPoints.of(2),
                calFrequentRenterPoints(PriceCode.ONE,DaysRented.TWO));
    }

    @Test
    void should_get_1_point_when_price_code_1_and_days_rented_3() {
        Assertions.assertEquals(
                FrequentRenterPoints.of(2),
                calFrequentRenterPoints(PriceCode.ONE,DaysRented.THREE));
    }

    @Test
    void should_get_1_point_when_price_code_is_two() {
        Assertions.assertEquals(
                FrequentRenterPoints.of(1),
                calFrequentRenterPoints(PriceCode.TWO,DaysRented.ONE));
    }

}