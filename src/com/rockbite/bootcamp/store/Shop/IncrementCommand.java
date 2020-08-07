package com.rockbite.bootcamp.store.Shop;

import com.rockbite.bootcamp.store.Command.IRedoUndo;
import com.rockbite.bootcamp.store.IInventory;
import com.rockbite.bootcamp.store.Product;

public class IncrementCommand implements IRedoUndo {
    private IInventory user;
    private Product product;
    private Shop shop;

    public  IncrementCommand(){

    }

    public IncrementCommand(IInventory user, Product product, Shop shop){
        this.user = user;
        this.product = product;
        this.shop = shop;
    }


    @Override
    public void execute() {
        shop.undoPurchase();
    }

    @Override
    public void undo() {
        shop.redoPurchase();
    }

    @Override
    public void reset() {

    }

    public IInventory getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }
}
