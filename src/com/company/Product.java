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
    private Resources resources;

    public Product(String name, Category category, Resources resources) {
        this.name = name;
        this.category = category;
        this.resources = resources;
    }


    public String getName() {
        return name;
    }
    public Resources getResources() {
        return resources;
    }
}
