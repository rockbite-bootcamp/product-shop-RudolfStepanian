package com.rockbite.bootcamp.store.Shop;

import com.rockbite.bootcamp.store.Command.CommandManager;
import com.rockbite.bootcamp.store.IInventory;
import com.rockbite.bootcamp.store.Pool.IPool;
import com.rockbite.bootcamp.store.Pool.Pool;
import com.rockbite.bootcamp.store.Product;
import com.rockbite.bootcamp.store.collections.Inventory.Inventory;
import com.rockbite.bootcamp.store.collections.Resources.Item;

public class Shop implements IShop, IPool {
    /**
     * identification for Shop
     */
    private int id;
    /**
     * list of products in shop with their prices
     */
    private Inventory productList = new Inventory();


    /**
     * operation in shop count
     */
    private Integer operationCounter = 0;
    /**
     * shop manager
     */
    public CommandManager manager = new CommandManager();
    public Pool<IncrementCommand> incrementCommandPool = new Pool<IncrementCommand>() {
        @Override
        protected IncrementCommand newObject() {
            return new IncrementCommand();
        }
    };
    public Pool<DecrementCommand> decrementCommandPool = new Pool<DecrementCommand>() {
        @Override
        protected DecrementCommand newObject() {
            return new DecrementCommand();
        }
    };






    /**
     * adding products to shop storage
     * @param product
     * @param count
     */
    public void addProduct(Product product, int count){
        productList.addItem(product, count);
    }

    /**
     * remove products frome storage
     * @param product
     * @param count
     */
    public void removeProduct(Product product, int count){
        productList.removeItem(product, count);
    }

    /**
     * get all products from storage
     * @return
     */
    public Inventory getProductList() {
        return productList;
    }

    @Override
    public void getProduct(Product product, int count) {

    }

    @Override
    public boolean canAfford(IInventory user, Product product) {
        if (productList.getCollection().get(product)!=null){
            for (Item item: product.getPrice().getCollection().keySet()) {
                int count = product.getPrice().getCollection().get(item);
                if (!user.hasItem(item, count)) {
                    return false;
                }
            }
        }
            return true;
    }

    @Override
    public void transaction(IInventory user, Product product) {

        if (product != null) {

            if (canAfford(user, product)) {
                for(Item item: product.getPrice().getCollection().keySet()) {
                    int count = product.getPrice().getCollection().get(item);
                    user.spendItem(item, count);
                }

                for (Item item: product.getPayload().getCollection().keySet()) {
                    int count = product.getPayload().getCollection().get(item);
                    user.addItem(item, count);
                }
                System.out.println("transaction completed");
                incrementCommandPool.obtain(new IncrementCommand(user, product,this));
                this.operationCounter++;
                return;
            }
        }
        System.out.println("transaction impossible");
        return;

    }

    public void undoPurchase(){
        manager.executeCommand
                (incrementCommandPool.obtain
                        (incrementCommandPool.usedObjects.get(operationCounter-1)));
    }

    public void redoPurchase(){

    }


    @Override
    public void reset() {
        this.operationCounter = 0;
    }
}
