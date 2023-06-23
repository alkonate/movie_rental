package movierental.domain;

public enum DaysRented {
    // might ask the domain expert obout the max rented days
    ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6);

    final int rentedDays;
    DaysRented(int days){
        rentedDays = days;
    }
    public int getDaysRented(){
       return rentedDays;
    }


    public boolean isOver(int days) {
        return getDaysRented() > days;
    }

    public int minus(int days) {
        if(isOver(days))
            return (rentedDays - days);
        throw new RuntimeException("Rented Days should more than "+ days +"days");
    }
}
