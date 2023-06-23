package movierental;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class Movie {
    private final String _title;
    private final PriceCode _priceCode;

    public Movie(String title, PriceCode priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public PriceCode getPriceCode() {
        return _priceCode;
    }
    public String getTitle() {
        return _title;
    }


    public enum MovieTypes {
        CHILDREN, NEW_RELEASE, REGULAR;
    }

    public static enum PriceCode {
        ZERO,ONE,TWO;
        public static PriceCode priceCodeOf(MovieTypes movieTypes){
            Objects.requireNonNull(movieTypes);
            return switch (movieTypes){
                case REGULAR -> PriceCode.ZERO;
                case NEW_RELEASE -> PriceCode.ONE;
                case CHILDREN -> PriceCode.TWO;
            };
        }
        public <R> R match(Supplier<R> priceCodeZERO, Supplier<R> priceCodeONE, Supplier<R> priceCodeTWO) {
            return switch (this){
                case ZERO -> priceCodeZERO.get();
                case ONE -> priceCodeONE.get();
                case TWO -> priceCodeTWO.get();
            };
        }
    }
}
