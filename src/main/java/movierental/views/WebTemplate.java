package movierental.views;

import movierental.domain.Amount;
import movierental.domain.Customer;
import movierental.domain.Movie;
import movierental.domain.StatementResult;
import movierental.helpers.List;
import movierental.helpers.Tuple;

public class WebTemplate implements Template{

   static private String template =
           "<h1>Rental Record for <em>"+ CUSTOMER_NAME_PARAM +"</em></h1>" +
           "<table>"+
                   "{body}" +
            "</table>" +
            "<p>Amount owed is <em>"+ RENTAL_TOTAL_AMOUNT_PARAM +"</em></p>" +
            "<p>You earned <em>"+ FREQUENT_RENTER_POINTS_PARAM +"</em> frequent renter points</p>";
   static private String table = "<tr>" +
                                    "<td>" + MOVIE_TITLE_PARAM +"</td>" +
                                    "<td>"+ RENTAL_AMOUNT_PARAM +"</td>" +
                                "</tr>";
    @Override
    public String render(Customer customer, StatementResult statementResult) {
        return template.replace(CUSTOMER_NAME_PARAM,customer.getName())
                .replace("{body}", renderTable(statementResult.getMoviesAndPrices()))
                .replace(RENTAL_TOTAL_AMOUNT_PARAM,statementResult.getTotalAmount().toString())
                .replace(FREQUENT_RENTER_POINTS_PARAM,statementResult.getFrequentRenterPoints().toString());
    }

    private String renderTable(List<Tuple<Movie, Amount>> moviesAndPrices) {
        return moviesAndPrices.foldLeft(
                new StringBuilder(),
                acc -> movieAndAmount ->
                        acc.append(
                                table.replace(MOVIE_TITLE_PARAM,movieAndAmount.get_1().getTitle())
                                        .replace(RENTAL_AMOUNT_PARAM,movieAndAmount.get_2().toString())
                        )
        ).toString();
    }
}
