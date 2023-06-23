package movierental.views;

import movierental.domain.*;
import movierental.helpers.List;
import movierental.helpers.Tuple;

public class CliTemplate implements Template {
    static final private String HEADER = "Rental Record for " + CUSTOMER_NAME_PARAM + "\n";
    static final private String BODY = "\t" + MOVIE_TITLE_PARAM + "\t" + RENTAL_AMOUNT_PARAM + "\n";
    static final private String FOOTER = "Amount owed is " + RENTAL_TOTAL_AMOUNT_PARAM + "\n" +
            "You earned " + FREQUENT_RENTER_POINTS_PARAM + " frequent renter points";

    @Override
    public String render(Customer customer, StatementResult rentalsResult) {
        return renderHeader(customer) +
                renderBody(rentalsResult.getMoviesAndPrices()) +
                renderFooter(rentalsResult.getTotalAmount(),rentalsResult.getFrequentRenterPoints());
    }

    private String renderHeader(Customer customer){
        return HEADER.replace(CUSTOMER_NAME_PARAM,customer.getName());
    }

    private String renderBody(List<Tuple<Movie, Amount>> moviesAndPrices){
        return moviesAndPrices.foldLeft(
                new StringBuilder(),
                bodyBuilder -> movieAndPrice ->
                bodyBuilder.append(
                        BODY.replace(MOVIE_TITLE_PARAM,movieAndPrice.get_1().getTitle())
                            .replace(RENTAL_AMOUNT_PARAM,movieAndPrice.get_2().toString())
                )
        ).toString();
    }
    private String renderFooter(Amount totalAmount, FrequentRenterPoints frequentRentPoints){
        return FOOTER.replace(RENTAL_TOTAL_AMOUNT_PARAM,totalAmount.toString())
                .replace(FREQUENT_RENTER_POINTS_PARAM,frequentRentPoints.toString());
    }
}
