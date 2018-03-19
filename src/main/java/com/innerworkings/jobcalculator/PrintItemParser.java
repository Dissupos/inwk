package com.innerworkings.jobcalculator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintItemParser implements Parser<PrintItem> {
    public static final String PRINT_ITEM_REGEX = "^([a-zA-Z\\s-]+)\\s(\\d+(.\\d+)?)(\\s(exempt))?$";

    @Override
    public PrintItem parse(String input) {

        Pattern pattern = Pattern.compile(PRINT_ITEM_REGEX);
        Matcher matcher = pattern.matcher(input.trim());

        if (matcher.matches()) {
            String name = matcher.group(1);
            BigDecimal price = new BigDecimal(matcher.group(2));
            boolean exempt = matcher.group(5) != null;
            return new PrintItem(name, price, exempt);
        }
        throw new ParserException("Input string is not in correct format");
    }
}
