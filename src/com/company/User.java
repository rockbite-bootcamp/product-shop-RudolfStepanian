package com.company;

import java.util.HashMap;

public class User {
    /**
     * identification for User
     */
    private int id;
    /**
     * identification for User
     */
    private Resources money;

    public User(Resources money) {
        this.money = money;
    }


    /**
     * function to buy one certain product from shop
     * @param currentShop
     * @param productToBuy
     */
    public void buyProduct(Shop currentShop, Product productToBuy){
        int shopStorageCount = currentShop.getProductList().getCount(productToBuy);
        if (shopStorageCount < 1){
            System.out.println("There is no such product");
            return;
        }
        Price productCurrency = productToBuy.getCost();
        int productPrice = productCurrency.getCount();
        int userMoney = money.getCount(productCurrency.getCurrency());
        if (userMoney >= productPrice){
            money.remove(productCurrency.getCurrency(), productPrice);
            currentShop.removeProduct(productToBuy,1);
        } else {
            System.out.println("not enough money");
        }
    }

    /**
     * buyProduct function override for multiple item purchase
     * @param currentShop
     * @param productToBuy
     * @param count
     */
    public void buyProduct(Shop currentShop, Product productToBuy, int count){
        int shopStorageCount = currentShop.getProductList().getCount(productToBuy);
        if (shopStorageCount < count){
            System.out.println("There is no such product");
            return;
        }
        Price productCurrency = productToBuy.getCost();
        int productPrice = productCurrency.getCount();
        int userMoney = money.getCount(productCurrency.getCurrency());
        if (userMoney >= productPrice*count){
            money.remove(productCurrency.getCurrency(), productPrice);
            currentShop.removeProduct(productToBuy,count);
        } else {
            System.out.println("not enough money");
        }
    }


    public void getMoney() {
        System.out.println(); money.toString();
    }
}
