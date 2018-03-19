package com.innerworkings.jobcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrintItemService {
    public static final int DEFAULT_TAX_VALUE = 7;

    public BigDecimal calculateItemTax(PrintItem printItem) {
        if (printItem.isFreeTax()) {
            return BigDecimal.ZERO;
        }
        BigDecimal price = printItem.getPrice();
        return BigDecimalUtils.calculatePercentOfValue(price, DEFAULT_TAX_VALUE);
    }

    public BigDecimal calculateSaleTax(PrintItem printItem) {
        return printItem.getPrice().add(calculateItemTax(printItem)).setScale(2, RoundingMode.HALF_EVEN);
    }

    public String stringFormat(PrintItem printItem) {
        return printItem.getName() + ": $" + calculateSaleTax(printItem);
    }
}
