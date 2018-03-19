package com.innerworkings.jobcalculator;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

public class FileServiceTest {
    private FileService fileService;

    public FileServiceTest() {
        this.fileService = new FileService();
    }

    @Test
    public void testReadJobFromFileHappy() {
        URL url = this.getClass().getClassLoader().getResource("inputHappy.txt");
        assertNotNull(url);
        Job job = null;
        try {
            job = fileService.readJobFromFile(url.getFile());
        } catch (IOException e) {
            fail("it's happy test");
        }

        assertTrue(job.isExtraMerge());
        assertEquals(2, job.getPrintItems().size());
    }

    @Test
    public void testReadJobFromFileError() {
        try {
            fileService.readJobFromFile("C:/input2.txt");
            fail("it's unhappy test");
        } catch (IOException e) {
            // good
        }
    }

    @Test
    public void testWriteToFileHappy() {
        URL url = this.getClass().getClassLoader().getResource("output.txt");
        assertNotNull(url);
        try {
            fileService.writeToFile(url.getFile(), "Happy");
        } catch (IOException e) {
            fail("it's happy test");
        }
    }

    @Test
    public void testWriteToFileUnhappy() {
        try {
            fileService.writeToFile("C:/output.txt", "Happy");
            fail("it's unhappy test");
        } catch (IOException e) {
            // good
        }
    }
}
