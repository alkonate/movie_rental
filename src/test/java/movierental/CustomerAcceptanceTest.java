package movierental;


import movierental.Movie.MovieTypes;
import movierental.helpers.List;
import movierental.views.CreateStatementController;
import movierental.views.CliTemplate;
import movierental.views.WebTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static movierental.Movie.PriceCode.priceCodeOf;

public class CustomerAcceptanceTest {

    private List<Rental> getRentals(){
        return  List.of(
                new Rental(new Movie("Jaws", priceCodeOf(MovieTypes.REGULAR)), DaysRented.TWO),
                new Rental(new Movie("Golden Eye",  priceCodeOf(MovieTypes.REGULAR)), DaysRented.THREE),
                new Rental(new Movie("Short New",  priceCodeOf(MovieTypes.NEW_RELEASE)), DaysRented.ONE),
                new Rental(new Movie("Long New", priceCodeOf(MovieTypes.NEW_RELEASE)), DaysRented.TWO),
                new Rental(new Movie("Bambi",  priceCodeOf(MovieTypes.CHILDREN)), DaysRented.THREE),
                new Rental(new Movie("Toy Story",  priceCodeOf(MovieTypes.CHILDREN)), DaysRented.FOUR)
        );
    }

    private Customer getCustomer(){
        return new Customer("Bob");
    }

    @Test
    public void should_get_cli_statement() {
        // given
        Customer customer = getCustomer();
        List<Rental> rentals = getRentals();
        // when
        String stmt = new CreateStatementController(new CliTemplate())
                .createStatement(customer,rentals);
        // then
        String expected =
                "Rental Record for Bob\n" +
                "\tJaws\t2.0\n" +
                "\tGolden Eye\t3.5\n" +
                "\tShort New\t3.0\n" +
                "\tLong New\t6.0\n" +
                "\tBambi\t1.5\n" +
                "\tToy Story\t3.0\n" +
                "Amount owed is 19.0\n" +
                "You earned 7 frequent renter points";

        Assertions.assertEquals(expected, stmt);
    }

    @Test
    public void should_get_web_statement() {
        // given
        Customer customer = getCustomer();
        List<Rental> rentals = getRentals();
        // when
        String stmt = new CreateStatementController(new WebTemplate())
                .createStatement(customer,rentals);
        //then
      String expected =  "<h1>Rental Record for <em>Bob</em></h1>" +
                          "<table>"+
                              "<tr><td>Jaws</td><td>2.0</td></tr>" +
                              "<tr><td>Golden Eye</td><td>3.5</td></tr>" +
                              "<tr><td>Short New</td><td>3.0</td></tr>" +
                              "<tr><td>Long New</td><td>6.0</td></tr>" +
                              "<tr><td>Bambi</td><td>1.5</td></tr>" +
                              "<tr><td>Toy Story</td><td>3.0</td></tr>" +
                          "</table>" +
                          "<p>Amount owed is <em>19.0</em></p>" +
                          "<p>You earned <em>7</em> frequent renter points</p>";

        Assertions.assertEquals(expected, stmt);
    }
}