package com.rockbite.bootcamp.store;

import com.rockbite.bootcamp.store.Shop.DecrementCommand;
import com.rockbite.bootcamp.store.Shop.IncrementCommand;
import com.rockbite.bootcamp.store.Shop.Shop;
import com.rockbite.bootcamp.store.collections.Resources.Item;

public class Main {

    public static void main(String[] args) {
        User U1 = new User();
        User U2 = new User();
        Item I = new Item("I");
        Product P = new Product("P");
        U1.addItem(I,500);
        U2.addItem(I,1000);
        P.addPrice(I,400);
        P.addPayload(I,450);
        /**
         * user purchase product
         */
        Shop.getShopInstance().transaction(U1,P);
        Shop.getShopInstance().transaction(U2,P);
        System.out.println("U1 : " + U1.toString());
        System.out.println("U2 : " + U2.toString());
        System.out.println(Shop.getShopInstance().incrementCommandPool.toString());

        /**
         * user return product to shop
         */
        System.out.println("U1 : " + U1.toString());
        System.out.println("U2 : " + U2.toString());
        U2.undoPurchase();
        U1.undoPurchase();
        System.out.println(Shop.getShopInstance().incrementCommandPool.toString());
//        Shop.getShopInstance().undoPurchase(U2);
//        Shop.getShopInstance().undoPurchase(U1);

        /**
         * user buys product again (redo function used)
         */
        System.out.println("U1 : " + U1.toString());
        System.out.println("U2 : " + U2.toString());
        U1.redoPurchase();
        U2.redoPurchase();
//        System.out.println(Shop.getShopInstance().incrementCommandPool.toString());
    }
}
