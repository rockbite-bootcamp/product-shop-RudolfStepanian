package com.company;

public class Price {
    /**
     * currency which will be used to buy item
     */
    private Currency currency;
    /**
     * amount of money needed to pay the price
     */
    private int count;

    public Price(Currency currency, int count) {
        this.currency = currency;
        this.count = count;
    }

    @Override
    public String toString() {
        return count + " " + currency.getName();
    }

    public Currency getCurrency() {
        return currency;
    }
    public int getCount() {
        return count;
    }
}
