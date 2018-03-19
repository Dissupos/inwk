package com.innerworkings.jobcalculator;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {
    public static final String QUIT_TOKEN = "quit";

    private JobService service;
    private Job job;

    public ConsoleInputReader(JobService service) {
        this.service = service;
        this.job = JobSingleton.getInstance();
    }


    @Override
    public void read() {
        printUsage();
        Scanner scanner = new Scanner(System.in);
        String command;
        Parser<PrintItem> parser = new PrintItemParser();
        while (!QUIT_TOKEN.equalsIgnoreCase(command = scanner.nextLine())) {
            if (command.isEmpty()) {
                continue;
            }

            command = command.trim();
            if (JobService.EXTRA_MARGIN_TOKEN.equalsIgnoreCase(command)) {
                job.setExtraMerge(!job.isExtraMerge());
                continue;
            } else {
                try {
                    job.getPrintItems().add(parser.parse(command));
                } catch (ParserException e) {
                    System.out.println("Exception:" + e.getMessage());
                    continue;
                }
            }
            System.out.println(service.stringFormat(job) + "\n");
        }
    }

    private void printUsage() {
        System.out.println("Welcome to Job calculator!");
        System.out.println("You may add \"items\" using format like this: \n" +
                "\textra-margin - for extra margin\n" +
                "\txxxxxxxx 000.00 exempt - (name using english alphabet in any case) whitespace (any number) (whitespace exempt if you want use free tax)  \n" +
                "Examples:\n\textra-margin\n\tenvelopes 520.00\n\tletterhead 1983.37 exempt");
        System.out.println("Write your commands:");
    }
}

