package com.rockbite.bootcamp.store;

import com.rockbite.bootcamp.store.Shop.DecrementCommand;
import com.rockbite.bootcamp.store.Shop.IncrementCommand;
import com.rockbite.bootcamp.store.Shop.Shop;
import com.rockbite.bootcamp.store.collections.Resources.Item;

public class Main {

    public static void main(String[] args) {
        Shop S = new Shop();
        User U = new User();
        Item I = new Item("I");
        Product P = new Product("P");

        U.addItem(I,500);
        P.addPrice(I,400);
        P.addPayload(I,450);
        S.transaction(U,P);

        System.out.println(S.incrementCommandPool.toString());

        S.undoPurchase();

//        S.manager.executeCommand(S.incrementCommandPool.obtain(S.incrementCommandPool.usedObjects.get(0)));
//
//        System.out.println(S.incrementCommandPool.toString());
//        System.out.println(S.incrementCommandPool.toString());


//        IncrementCommand yv = S.incrementCommandPool.obtain();
//        IncrementCommand ub = S.incrementCommandPool.obtain();
//        S.decrementCommandPool.obtain();

//        S.manager.undo();


    }
}
