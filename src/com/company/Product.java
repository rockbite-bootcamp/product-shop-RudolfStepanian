package com.company;

public class Product {
    /**
     * identification for Product
     */
    private int id;
    /**
     * product name
     */
    private String name;
    /**
     * product category
     */
    private Category category;
    /**
     * Resources lost after purchase
     */
    private Price cost;

    public Product(String name, Category category, Price cost) {
        this.name = name;
        this.category = category;
        this.cost = cost;
    }


    public String getName() {
        return name;
    }
    public Price getCost() {
        return cost;
    }
}
