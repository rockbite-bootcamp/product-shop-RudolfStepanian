package com.rockbite.bootcamp.store;

import com.rockbite.bootcamp.store.Shop.DecrementCommand;
import com.rockbite.bootcamp.store.Shop.IncrementCommand;
import com.rockbite.bootcamp.store.Shop.Shop;
import com.rockbite.bootcamp.store.collections.Resources.Item;

public class Main {

    public static void main(String[] args) {
        /**
         * creating instances to work with
         */
        Shop S = new Shop();
        User U = new User();
        Item I = new Item("I");
        Product P = new Product("P");
        U.addItem(I,500);
        P.addPrice(I,400);
        P.addPayload(I,450);
        /**
         * user purchase product
         */
        S.transaction(U,P);
        System.out.println(U.toString());
        System.out.println(S.incrementCommandPool.toString());

        /**
         * user return product to shop
         */
        S.undoPurchase();
        System.out.println(U.toString());
        System.out.println(S.incrementCommandPool.toString());

        /**
         * user buys product again (redo function used)
         */
        S.redoPurchase();
        System.out.println(U.toString());
        System.out.println(S.incrementCommandPool.toString());
    }
}
