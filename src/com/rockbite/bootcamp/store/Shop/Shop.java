package com.rockbite.bootcamp.store.Shop;

import com.rockbite.bootcamp.store.IInventory;
import com.rockbite.bootcamp.store.Pool.IPool;
import com.rockbite.bootcamp.store.Pool.Pool;
import com.rockbite.bootcamp.store.Product;
import com.rockbite.bootcamp.store.User;
import com.rockbite.bootcamp.store.collections.Inventory.Inventory;
import com.rockbite.bootcamp.store.collections.Resources.Item;

import java.util.HashMap;

public class Shop implements IShop, IPool {
    private static Shop shopInstance;
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
    private Integer reOperationCounter = 0;

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

    private Shop(){}
    public static Shop getShopInstance(){
        if(shopInstance == null){
            shopInstance = new Shop();
        }
        return shopInstance;
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

    /**
     * adding products to shop storage
     * @param product
     * @param count
     */
    @Override
    public void getProduct(Product product, int count) {
        productList.addItem(product, count);
    }

    @Override
    public boolean canAfford(IInventory user, Product product) {
        if ( productList.getCollection().get(product) != null ) {
            for ( Item item: product.getPrice().getCollection().keySet() ) {
                int count = product.getPrice().getCollection().get(item);
                if (!user.hasItem(item, count)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean canReturn(IInventory user, Product product) {
        if ( productList.getCollection().get(product) != null ) {
            for ( Item item: product.getPayload().getCollection().keySet() ) {
                int count = product.getPayload().getCollection().get(item);
                if (!user.hasItem(item, count)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean transaction(IInventory user, Product product) {

        if (product != null) {

            if ( canAfford(user, product) ) {
                for ( Item item: product.getPrice().getCollection().keySet() ) {
                    int count = product.getPrice().getCollection().get(item);
                    user.spendItem(item, count);
                }

                for ( Item item: product.getPayload().getCollection().keySet() ) {
                    int count = product.getPayload().getCollection().get(item);
                    user.addItem(item, count);
                }
                System.out.println("transaction completed");
                IncrementCommand transaction = new IncrementCommand(user, product);
                user.setLastTransaction(transaction);
                incrementCommandPool.obtain(transaction);
                this.operationCounter++;
                return true;
            }
        }
        System.out.println("transaction impossible");
        return false;

    }

    public boolean takeBackProduct(IInventory user, Product product){
        if (product != null) {
            if ( canReturn(user, product) ) {
                for ( Item item: product.getPayload().getCollection().keySet() ) {
                    int count = product.getPayload().getCollection().get(item);
                    user.spendItem(item, count);
                }

                for ( Item item: product.getPrice().getCollection().keySet() ) {
                    int count = product.getPrice().getCollection().get(item);
                    user.addItem(item, count);
                }
                System.out.println("transaction completed");
                this.operationCounter++;
                return true;
            }
        }
        System.out.println("transaction impossible");
        return false;
    }

    @Override
    public void reset() {
        this.operationCounter = 0;
    }
}
