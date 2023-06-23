package movierental;

import movierental.domain.Amount;
import movierental.domain.DaysRented;
import movierental.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static movierental.domain.Movie.PriceCode.priceCodeOf;
import static movierental.domain.RentalAmountCalculator.calRentalAmount;

@DisplayName("Movie price code 2")
class MoviePriceCodeTwoTest {
    @Test
    void should_get_a_rental_amount_of_1p5_when_rented_days_are_three() {
        Assertions.assertEquals(
                Amount.of(1.5),
                calRentalAmount(priceCodeOf(Movie.MovieTypes.CHILDREN), DaysRented.THREE));
    }

    @Test
    void should_get_a_rental_amount_of_3_when_rented_days_are_four() {
        Assertions.assertEquals(
                Amount.of(3),
                calRentalAmount(priceCodeOf(Movie.MovieTypes.CHILDREN), DaysRented.FOUR));
    }
}
