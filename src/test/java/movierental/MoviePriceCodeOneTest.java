package movierental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static movierental.Movie.PriceCode.priceCodeOf;
import static movierental.RentalAmountCalculator.calRentalAmount;

@DisplayName("Movie price code 1")
class MoviePriceCodeOneTest {
    @Test
    void should_get_a_rental_amount_of_9_when_rented_days_are_three() {
        Assertions.assertEquals(
                Amount.of(9),
                calRentalAmount(priceCodeOf(Movie.MovieTypes.NEW_RELEASE), DaysRented.THREE));
    }
}
