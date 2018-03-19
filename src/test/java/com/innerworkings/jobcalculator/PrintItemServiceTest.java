package com.innerworkings.jobcalculator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PrintItemServiceTest {
    private PrintItemService service;

    public PrintItemServiceTest() {
        this.service = new PrintItemService();
    }

    @Test
    public void testCalculateItemTax() {
        PrintItem item = new PrintItem("envelopes", new BigDecimal(520));
        assertEquals(new BigDecimal("36.40"), service.calculateItemTax(item));
        item = new PrintItem("letterhead", new BigDecimal(1983.37), true);
        assertEquals(new BigDecimal("0"), service.calculateItemTax(item));

    }

    @Test
    public void testCalculateSaleTax() {
        PrintItem item = new PrintItem("envelopes", new BigDecimal(520));
        assertEquals(new BigDecimal("556.40"), service.calculateSaleTax(item));
        item = new PrintItem("letterhead", new BigDecimal(1983.37), true);
        assertEquals(new BigDecimal("1983.37"), service.calculateSaleTax(item));
    }

    @Test
    public void test3() {
        PrintItem item = new PrintItem("envelopes", new BigDecimal(520));
        assertEquals("envelopes: $556.40", service.stringFormat(item));

    }
}
