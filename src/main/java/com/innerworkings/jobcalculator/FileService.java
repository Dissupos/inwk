package com.innerworkings.jobcalculator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileService {

    public Job readJobFromFile(String filepath) throws IOException {
        File file = new File(filepath);
        Scanner sc = new Scanner(file);


        Parser<PrintItem> printItemParser = new PrintItemParser();
        Job job = new Job();
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (JobService.EXTRA_MARGIN_TOKEN.equalsIgnoreCase(command)) {
                job.setExtraMerge(true);
            } else {
                try {
                    job.getPrintItems().add(printItemParser.parse(command));
                } catch (ParserException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
        }
        return job;
    }

    public void writeToFile(String filepath, String text) throws IOException {

        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(filepath);
        byte[] strToBytes = text.getBytes();
        outputStream.write(strToBytes);

        outputStream.close();
    }

}