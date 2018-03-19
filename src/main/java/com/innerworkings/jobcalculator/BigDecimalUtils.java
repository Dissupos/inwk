package com.innerworkings.jobcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {
    public static final BigDecimal FIFTY = BigDecimal.valueOf(50);
    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    public static BigDecimal scaleAndRoundValueToTheNearestEvenCent(BigDecimal bigDecimal) {
        return bigDecimal.multiply(FIFTY).setScale(0, RoundingMode.DOWN)
                .divide(FIFTY, 2, RoundingMode.UNNECESSARY);
    }

    public static BigDecimal calculatePercentOfValue(BigDecimal value, int percent) {
        return value.multiply(BigDecimal.valueOf(percent).setScale(2, RoundingMode.CEILING))
                .divide(ONE_HUNDRED, RoundingMode.CEILING);
    }
}
