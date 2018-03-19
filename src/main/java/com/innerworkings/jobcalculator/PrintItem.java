package com.innerworkings.jobcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrintItem {

    private boolean freeTax;
    private String name;
    private BigDecimal price;

    private PrintItem() {
        freeTax = false;
    }

    public PrintItem(String name, BigDecimal price) {
        this();
        this.name = name;
        this.price = price;
    }

    public PrintItem(String name, BigDecimal price, boolean freeTax) {
        this(name, price);
        this.freeTax = freeTax;
    }

    public boolean isFreeTax() {
        return freeTax;
    }

    public void setFreeTax(boolean freeTax) {
        this.freeTax = freeTax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PrintItem{" +
                "freeTax=" + freeTax +
                ", name='" + name + '\'' +
                ", price=" + price.setScale(2, RoundingMode.CEILING) +
                '}';
    }
}
