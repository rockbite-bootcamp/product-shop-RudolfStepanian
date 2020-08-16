package com.rockbite.bootcamp.store.Shop;

import com.rockbite.bootcamp.store.Command.ICommannd;
import com.rockbite.bootcamp.store.IInventory;
import com.rockbite.bootcamp.store.Product;

public class IncrementCommand implements ICommannd {
    private IInventory user;
    private Product product;

    public IncrementCommand(){}

    public IncrementCommand(IInventory user, Product product){
        this.user = user;
        this.product = product;
    }


    @Override
    public void execute() {
        Shop.getShopInstance().transaction(user, user.getLastTransaction().getProduct());
    }

    @Override
    public void undo() {
        Shop.getShopInstance().takeBackProduct(user, user.getLastTransaction().getProduct());
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

    @Override
    public String toString() {
        return "IncrementCommand : user=" + user + ", product=" + product;
    }
}
