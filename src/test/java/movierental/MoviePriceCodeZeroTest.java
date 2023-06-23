package movierental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static movierental.Movie.PriceCode.priceCodeOf;
import static movierental.RentalAmountCalculator.calRentalAmount;

@DisplayName("Movie price code 0")
class MoviePriceCodeZeroTest {
    @Test
    void should_get_a_rental_amount_of_2_when_rented_days_are_equal_two() {
        Assertions.assertEquals(
                Amount.of(2),
                calRentalAmount(priceCodeOf(Movie.MovieTypes.REGULAR), DaysRented.TWO));
    }

    @Test
    void should_get_a_rental_amount_of_3p5_when_rented_days_are_three() {
        Assertions.assertEquals(
                Amount.of(3.5),
                calRentalAmount(priceCodeOf(Movie.MovieTypes.REGULAR), DaysRented.THREE));
    }
}
