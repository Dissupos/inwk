package com.innerworkings.jobcalculator;

import java.io.IOException;
import java.math.BigDecimal;

public class JobService {

    public static final int EXTRA_MERGE_PERCENT = 5;
    public static final int DEFAULT_MERGE_PERCENT = 11;
    public static final String EXTRA_MARGIN_TOKEN = "extra-margin";

    private final PrintItemService printItemService;
    private final FileService fileService;

    public JobService(PrintItemService printItemService, FileService fileService) {
        this.printItemService = printItemService;
        this.fileService = fileService;
    }

    public BigDecimal calculateTotal(Job job) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal taxes = BigDecimal.ZERO;
        for (PrintItem printItem : job.getPrintItems()) {
            BigDecimal itemTax = printItemService.calculateItemTax(printItem);
            BigDecimal itemPrice = printItem.getPrice();
            taxes = taxes.add(itemTax);
            result = result.add(itemPrice);
        }

        final int mergePercent = job.isExtraMerge() ? DEFAULT_MERGE_PERCENT + EXTRA_MERGE_PERCENT : DEFAULT_MERGE_PERCENT;

        result = result
                .add(BigDecimalUtils.calculatePercentOfValue(result, mergePercent)).add(taxes);

        return BigDecimalUtils.scaleAndRoundValueToTheNearestEvenCent(result);
    }

    public String stringFormat(Job job) {
        StringBuilder builder = new StringBuilder();
        for (PrintItem printItem : job.getPrintItems()) {
            builder.append(printItemService.stringFormat(printItem)).append("\n");
        }
        builder.append("total: $").append(calculateTotal(job));
        return builder.toString();
    }

    public void calculateToFile(String inputFilePath, String outputFilePath) throws IOException {
        Job job = fileService.readJobFromFile(inputFilePath);
        fileService.writeToFile(outputFilePath, this.stringFormat(job));
    }


}
