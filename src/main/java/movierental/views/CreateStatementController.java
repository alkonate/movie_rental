package movierental.views;

import movierental.Customer;
import movierental.Rental;
import movierental.application.Statement;
import movierental.helpers.List;

public class CreateStatementController {

    private final Template template;
    public CreateStatementController(Template template){
        this.template = template;
    }
    public String createStatement(Customer customer, List<Rental> rentals){
        return Statement.create(rentals)
                .fold(rentalsResult -> template.render(customer,rentalsResult));
    }
}
