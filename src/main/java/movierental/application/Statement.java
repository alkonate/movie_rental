package movierental.application;

import movierental.*;
import movierental.helpers.List;
import movierental.helpers.Tuple;

public class Statement {
    public static StatementResult create(List<Rental> rentals) {
      return rentals.foldRight(
                StatementResult.EMPTY,
              rental -> rentalResultAcc ->
                        rentalResultAcc.map(
                                movies -> totalAmount -> frequentRentPoints -> {
                                    Amount movieRentalPrice = rental.calculatePrice();
                                    FrequentRenterPoints renterPoints = rental.calculateRenterPoints();
                                    return StatementResult.of(
                                            movies.prepend(Tuple.of(rental.getMovie(),movieRentalPrice)),
                                            totalAmount.add(movieRentalPrice),
                                            frequentRentPoints.add(renterPoints)
                                    );
                                }
        ));
    }
}
