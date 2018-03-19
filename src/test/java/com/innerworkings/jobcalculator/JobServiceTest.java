package com.innerworkings.jobcalculator;

import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class JobServiceTest {
    private JobService service;

    public JobServiceTest() {
        this.service = new JobService(new PrintItemService(), new FileService());
    }

    @Test
    public void testCalculateTotal() {
        Job job = new Job();
        job.setExtraMerge(true);
        job.getPrintItems().add(new PrintItem("envelopes", new BigDecimal(520)));
        job.getPrintItems().add(new PrintItem("letterhead", new BigDecimal(1983.37), true));

        BigDecimal result = service.calculateTotal(job);
        assertEquals(new BigDecimal("2940.30"), result);
    }

    @Test
    public void testStringFormat() {
        Job job = new Job();
        job.setExtraMerge(true);
        job.getPrintItems().add(new PrintItem("envelopes", new BigDecimal(520)));
        job.getPrintItems().add(new PrintItem("letterhead", new BigDecimal(1983.37), true));
        String result = "envelopes: $556.40\n" +
                "letterhead: $1983.37\n" +
                "total: $2940.30";
        assertEquals(result, service.stringFormat(job));
    }

    @Test
    public void testCalculateToFileHappy() {
        URL urlInput = this.getClass().getClassLoader().getResource("inputHappy.txt");
        URL urlOutput = this.getClass().getClassLoader().getResource("output.txt");
        assertNotNull(urlInput);
        assertNotNull(urlOutput);
        try {
            service.calculateToFile(urlInput.getFile(), urlOutput.getFile());
        } catch (IOException e) {
            fail("it's happy test");
        }
    }

    @Test
    public void testCalculateToFileFail() {
        URL urlOutput = this.getClass().getClassLoader().getResource("output.txt");
        assertNotNull(urlOutput);
        try {
            service.calculateToFile("C:/input1.txt", urlOutput.getFile());
            fail("it's unhappy test");
        } catch (IOException e) {
            // good
        }
    }

}
