package com.rockbite.bootcamp.store;

import com.rockbite.bootcamp.store.Command.ICommannd;
import com.rockbite.bootcamp.store.Shop.IncrementCommand;
import com.rockbite.bootcamp.store.Shop.Shop;
import com.rockbite.bootcamp.store.collections.Resources.Item;
import com.rockbite.bootcamp.store.collections.Resources.Resources;

import java.util.HashMap;

public class User implements IInventory {
    /**
     * identification for User
     */
    private int id;
    /**
     * all user items with counts
     */
    private Resources itemList;

    private IncrementCommand LastTransaction = null;

    public User() {
        this.itemList = new Resources();
    }

    public void showMoney() {
        System.out.println(itemList.toString());
    }

    public Resources getInventory() {
        return itemList;
    }

    public void setLastTransaction(IncrementCommand lastTransaction) {
        this.LastTransaction = lastTransaction;
    }

    @Override
    public IncrementCommand getLastTransaction() {
        return null;
    }

    public void undoPurchase(){
        Shop.getShopInstance().takeBackProduct(this,this.LastTransaction.getProduct());
        Shop.getShopInstance().incrementCommandPool.free(this.LastTransaction);
    }

    public void redoPurchase(){
        Shop.getShopInstance().transaction(this,this.LastTransaction.getProduct());
    }

    @Override
    public boolean hasItem(Item item, int count) {
        if(!itemList.getCollection().containsKey(item)) {
            return false;
        }
        return itemList.getCount(item) >= count;
    }

    @Override
    public void spendItem(Item item, int count) {
        if(hasItem(item,count)){
            itemList.removeItem(item,count);
        }
    }

    @Override
    public void addItem(Item item, int count) {
        itemList.addItem(item,count);
    }

    @Override
    public String toString() {
        return "money = " + itemList.toString();
    }
}
