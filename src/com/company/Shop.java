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


    public void purchaseProduct(User user, Product productToSell, int count){
        int shopStorageCount = getProductList().getCount(productToSell);
        if (shopStorageCount < count){
            System.out.println("There is no such product");
            return;
        }

        boolean moneyEnough = true;
        Resources productCurrency = productToSell.getResourcesLost();
        Resources productGain = productToSell.getResourcesGained();
        for(Currency type: productCurrency.collection.keySet()){
            int userMoney = user.getMoney().getCount(type);
            if(userMoney >= productToSell.getResourcesLost().getCount(type) * count){
                continue;
            }
            moneyEnough = false;
        }
        if (moneyEnough){
            for(Currency type: productCurrency.collection.keySet()){
                user.getMoney().remove(type, productToSell.getResourcesLost().getCount(type));
            }
            for(Currency type: productGain.collection.keySet()){
                user.getMoney().add(type, productToSell.getResourcesGained().getCount(type));
            }
            removeProduct(productToSell,count);
        } else {
            System.out.println("not enough money");
        }

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
