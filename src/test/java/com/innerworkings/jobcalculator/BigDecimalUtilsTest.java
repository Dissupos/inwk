package com.innerworkings.jobcalculator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BigDecimalUtilsTest {

    @Test
    public void testCalculatePercentOfValue() {
        BigDecimal result = BigDecimalUtils.calculatePercentOfValue(new BigDecimal("10"), 10);
        assertEquals(0, new BigDecimal(1).compareTo(result));
    }

    @Test
    public void testScaleAndRoundValueToTheNearestEvenCent() {
        BigDecimal result = BigDecimalUtils.scaleAndRoundValueToTheNearestEvenCent(new BigDecimal("123.13"));
        assertEquals(new BigDecimal("123.12"), result);
    }
}
