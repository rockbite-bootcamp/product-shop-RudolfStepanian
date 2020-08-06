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
    private Resources resourcesLost;
    /**
     * resources gained via buing product
     */
    private Resources resourcesGained;

    public Product(String name, Category category, Resources resourcesLost, Resources resourcesGained) {
        this.name = name;
        this.category = category;
        this.resourcesLost = resourcesLost;
        this.resourcesGained = resourcesGained;
    }


    public String getName() {
        return name;
    }
    public Resources getResourcesLost() {
        return resourcesLost;
    }
    public Resources getResourcesGained() {
        return resourcesGained;
    }
}
