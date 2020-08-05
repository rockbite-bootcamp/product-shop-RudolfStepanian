package com.company;

import java.util.HashMap;

public class Shop {
    /**
     * identification for Shop
     */
    private int id;
    /**
     * list of products in shop with their prices
     */
    private Storage productList = new Storage();


    /**
     * adding products to shop storage
     * @param product
     * @param count
     */
    public void addProduct(Product product, int count){
        productList.add(product, count);
    }


    public void getProduct(Category catt){

    }


    public void purchaseProduct(){

    }

    /**
     * remove products frome storage
     * @param product
     * @param count
     */
    public void removeProduct(Product product, int count){
        productList.remove(product, count);
    }

    /**
     * get all products from storage
     * @return
     */
    public Storage getProductList() {
        return productList;
    }
}
