package movierental.views;

import movierental.domain.Amount;
import movierental.domain.FrequentRenterPoints;
import movierental.domain.Movie;
import movierental.helpers.List;
import movierental.helpers.Tuple;

import java.util.function.Function;

public class Renderer {
    public static Function<List<Tuple<Movie,Amount>>, Function<Amount, Function<FrequentRenterPoints, String>>> render(Template template){
        return movies -> totalAmount -> frequentRentPoints -> null;
    }
}
