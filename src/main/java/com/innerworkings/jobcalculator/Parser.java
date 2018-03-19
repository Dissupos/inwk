package com.innerworkings.jobcalculator;

public interface Parser<T> {

    T parse(String input);
}
