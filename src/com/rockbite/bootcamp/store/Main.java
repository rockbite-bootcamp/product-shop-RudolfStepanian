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
        S.transaction(U1,P);
        S.transaction(U2,P);
        System.out.println("U1 : " + U1.toString());
        System.out.println("U2 : " + U2.toString());
        System.out.println(S.incrementCommandPool.toString());

        /**
         * user return product to shop
         */
//        S.undoPurchase();
        System.out.println("U1 : " + U1.toString());
        System.out.println("U2 : " + U2.toString());
        System.out.println(S.incrementCommandPool.toString());
        S.undoPurchase(U2);
        S.undoPurchase(U1);

        /**
         * user buys product again (redo function used)
         */
        S.redoPurchase();
        System.out.println("U1 : " + U1.toString());
        System.out.println("U2 : " + U2.toString());
        System.out.println(S.incrementCommandPool.toString());
    }
}
