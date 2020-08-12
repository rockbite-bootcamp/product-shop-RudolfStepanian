package com.rockbite.bootcamp.store.Shop;

import com.rockbite.bootcamp.store.IInventory;
import com.rockbite.bootcamp.store.Product;

public interface IShop {
    /**
     * function adds certain quantity of product to store
     * @param it
     * @param count
     */
    void getProduct(Product it, int count);

    /**
     * return boolean value in case if user can afford it
     * @param user
     * @param it
     * @return
     */
    boolean canAfford(IInventory user, Product it);

    /**
     * transaction function
     * @param user
     * @param it
     * @return
     */
    boolean transaction(IInventory user, Product it);
}
