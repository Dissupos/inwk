package com.innerworkings.jobcalculator;

import java.util.ArrayList;
import java.util.List;

public class Job {
    private boolean extraMerge;
    private List<PrintItem> printItems;

    public Job() {
        extraMerge = false;
        printItems = new ArrayList<PrintItem>();
    }

    public Job(boolean extraMerge) {
        this();
        this.extraMerge = extraMerge;
    }

    public boolean isExtraMerge() {
        return extraMerge;
    }

    public void setExtraMerge(boolean extraMerge) {
        this.extraMerge = extraMerge;
    }

    public List<PrintItem> getPrintItems() {
        return printItems;
    }

    public void setPrintItems(List<PrintItem> printItems) {
        this.printItems = printItems;
    }

    @Override
    public String toString() {
        return "Job{" +
                "extraMerge=" + extraMerge +
                ", printItems=" + printItems +
                '}';
    }
}
