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
        System.out.println(user.toString()+product.toString());
    }

    @Override
    public void undo() {
    }

    @Override
    public void reset() {

    }
}
