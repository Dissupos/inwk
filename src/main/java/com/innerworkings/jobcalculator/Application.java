package com.innerworkings.jobcalculator;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        JobService jobService = new JobService(new PrintItemService(), new FileService());
        if (args.length == 0) {
            InputReader inputReader = new ConsoleInputReader(jobService);
            inputReader.read();
        } else if (args.length == 2) {
            try {
                jobService.calculateToFile(args[0], args[1]);
            } catch (IOException e) {
                System.out.println("Exception:" + e.getMessage());
            }
        } else {
            System.out.println("Incorrect arguments!");
        }
    }
}
