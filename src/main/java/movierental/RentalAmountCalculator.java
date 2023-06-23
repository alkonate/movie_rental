package movierental;

import movierental.Movie.PriceCode;

class RentalAmountCalculator {
    private static Amount calRentalAmountPriceCodeZero(DaysRented daysRented) {
        return Amount.zero()
                .add(Amount.of(2))
                .when(
                        daysRented.isOver(2),
                        amount -> amount.add(new Amount(daysRented.minus(2) * 1.5))
                );
    }

    private static Amount calRentalAmountPriceCodeOne(DaysRented daysRented) {
        return Amount.zero()
                .add(Amount.of(daysRented.getDaysRented() * 3));
    }

    private static Amount calRentalAmountPriceCodeTwo(DaysRented daysRented) {
        return Amount.zero()
                .add(Amount.of(1.5))
                .when(
                        daysRented.isOver(3),
                        amount -> amount.add(new Amount(daysRented.minus(3) * 1.5)));
    }

    public static Amount calRentalAmount(PriceCode priceCode, DaysRented daysRented) {
        return priceCode
                .match(
                        // when PriceCode == ZERO
                        () -> calRentalAmountPriceCodeZero(daysRented),
                        // when PriceCode == ONE
                        () -> calRentalAmountPriceCodeOne(daysRented),
                        // when PriceCode == TWO
                        () -> calRentalAmountPriceCodeTwo(daysRented)
                );
    }

    //determine amounts for each line
//        switch (rental.getMovie().getPriceCode()) {
//            case ZERO -> {
//                currentAmount = currentAmount.add(new Amount(2));
//                if (rental.getDaysRented() > 2)
//                 currentAmount = currentAmount.add(new Amount((rental.getDaysRented() - 2) * 1.5));
//            }
//            case ONE -> currentAmount = currentAmount.add(new Amount(rental.getDaysRented() * 3));
//
//            case TWO -> {
//                currentAmount = currentAmount.add(new Amount(1.5));
//                if (rental.getDaysRented() > 3)
//                   currentAmount = currentAmount.add(new Amount((rental.getDaysRented() - 3) * 1.5));
//            }
//        }
//        return currentAmount;

}
