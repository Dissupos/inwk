package com.innerworkings.jobcalculator;

public class JobSingleton {
    private static Job job;
    
    private JobSingleton() {
    }

    public static Job getInstance() {
        if (job == null) {
            job = new Job();
        }
        return job;
    }

    public static void reset() {
        job = new Job();
    }
}
