package com.innerworkings.jobcalculator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PrintItemParserTest {
    private Parser<PrintItem> parser;

    public PrintItemParserTest() {
        this.parser = new PrintItemParser();
    }

    @Test
    public void testParseHappy() {
        PrintItem result = parser.parse("envelopes 520.00");
        assertEquals("envelopes", result.getName());
        assertEquals(new BigDecimal("520.00"), result.getPrice());
        assertFalse(result.isFreeTax());
        result = parser.parse("letterhead 1983.37 exempt");
        assertEquals("letterhead", result.getName());
        assertEquals(new BigDecimal("1983.37"), result.getPrice());
        assertTrue(result.isFreeTax());
    }

    @Test
    public void testParseFail() {
        try {
            parser.parse("bad input");
            fail("it's unhappy test");
        } catch (ParserException e) {
            //good
        }

    }
}
