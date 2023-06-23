package movierental.views;

import movierental.domain.Customer;
import movierental.domain.StatementResult;

public interface Template {
    public static String CUSTOMER_NAME_PARAM = "%customerName";
    public static String MOVIE_TITLE_PARAM = "%movieTitle";
    public static String RENTAL_AMOUNT_PARAM = "%rentalAmount";
    public static String RENTAL_TOTAL_AMOUNT_PARAM = "%rentalsTotalAmount";
    public static String FREQUENT_RENTER_POINTS_PARAM = "%frequentRenterPoints";
    public String render(Customer customer, StatementResult rentalsResult);
}
