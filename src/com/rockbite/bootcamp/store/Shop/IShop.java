package com.rockbite.bootcamp.store.Shop;

import com.rockbite.bootcamp.store.IInventory;
import com.rockbite.bootcamp.store.Product;

public interface IShop {
    void getProduct(Product it, int count);
    boolean canAfford(IInventory user, Product it);
    void transaction(IInventory user, Product it);
}
