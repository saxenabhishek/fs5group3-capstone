package shared;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Helper {
    public static BigDecimal makeBigDecimal(String value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_DOWN);
    }
}
