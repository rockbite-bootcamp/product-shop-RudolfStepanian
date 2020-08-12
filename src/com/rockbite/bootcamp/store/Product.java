package com.rockbite.bootcamp.store;

import com.rockbite.bootcamp.store.collections.Resources.Item;
import com.rockbite.bootcamp.store.collections.Resources.Resources;

public class Product {
    private final String name;

    private Resources payload = new Resources();

    private Resources price = new Resources();

    public Product(String name) {
        this.name = name;
    }

    public void addPrice(Item item, Integer count){
        price.addItem(item, count);
    }
    public void addPayload(Item item, Integer count){
        payload.addItem(item, count);
    }

    public Resources getPayload() {
        return payload;
    }
    public Resources getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
