package movierental;

import movierental.domain.Amount;
import movierental.domain.DaysRented;
import movierental.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static movierental.domain.Movie.PriceCode.priceCodeOf;
import static movierental.domain.RentalAmountCalculator.calRentalAmount;

@DisplayName("Movie price code 1")
class MoviePriceCodeOneTest {
    @Test
    void should_get_a_rental_amount_of_9_when_rented_days_are_three() {
        Assertions.assertEquals(
                Amount.of(9),
                calRentalAmount(priceCodeOf(Movie.MovieTypes.NEW_RELEASE), DaysRented.THREE));
    }
}
