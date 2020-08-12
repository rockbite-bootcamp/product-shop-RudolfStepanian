package com.rockbite.bootcamp.store.Shop;

import com.rockbite.bootcamp.store.Command.CommandManager;
import com.rockbite.bootcamp.store.IInventory;
import com.rockbite.bootcamp.store.Pool.IPool;
import com.rockbite.bootcamp.store.Pool.Pool;
import com.rockbite.bootcamp.store.Product;
import com.rockbite.bootcamp.store.User;
import com.rockbite.bootcamp.store.collections.Inventory.Inventory;
import com.rockbite.bootcamp.store.collections.Resources.Item;

import java.util.HashMap;

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
    private Integer reOperationCounter = 0;
    /**
     * shop manager
     */
    public CommandManager manager = new CommandManager();
    /**
     * List of last order from customers
     */
    public HashMap<IInventory, Integer> customersList = new HashMap<>();

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
                incrementCommandPool.obtain(new IncrementCommand(user, product,this));
                this.customersList.put(user,this.operationCounter);
                this.operationCounter++;
                return true;
            }
        }
        System.out.println("transaction impossible");
        return false;

    }

    public void undoPurchase(User user){
        if( this.operationCounter < 1 ) {
            return;
        }
        int index = this.customersList.get(user);
        IncrementCommand data = incrementCommandPool.usedObjects.get(index);
        System.out.println(data);
        IInventory userToReturn = data.getUser();
        Product productToReturn = data.getProduct();
        for ( Item item: productToReturn.getPrice().getCollection().keySet() ) {
            int count = productToReturn.getPrice().getCollection().get(item);
            userToReturn.addItem(item, count);
        }
        for ( Item item: productToReturn.getPayload().getCollection().keySet() ) {
            int count = productToReturn.getPayload().getCollection().get(item);
            userToReturn.spendItem(item, count);
        }
        incrementCommandPool.free(incrementCommandPool.usedObjects.get(operationCounter-1));
        this.operationCounter--;
        this.reOperationCounter++;
    }

    public void undoPurchase(){
        if( this.operationCounter < 1 ) {
            return;
        }
        IncrementCommand data = incrementCommandPool.usedObjects.get(operationCounter-1);
        IInventory userToReturn = data.getUser();
        Product productToReturn = data.getProduct();
        for( Item item: productToReturn.getPrice().getCollection().keySet() ) {
            int count = productToReturn.getPrice().getCollection().get(item);
            userToReturn.addItem(item, count);
        }
        for ( Item item: productToReturn.getPayload().getCollection().keySet() ) {
            int count = productToReturn.getPayload().getCollection().get(item);
            userToReturn.spendItem(item, count);
        }
        incrementCommandPool.free(incrementCommandPool.usedObjects.get(operationCounter-1));
        this.operationCounter--;
        this.reOperationCounter++;
    }

    public void redoPurchase(){
        if( this.reOperationCounter < 1 ) {
            return;
        }
        IncrementCommand data = incrementCommandPool.freeObjects.get(reOperationCounter-1);
        incrementCommandPool.obtain(incrementCommandPool.freeObjects.get(reOperationCounter-1));
        transaction(data.getUser(),data.getProduct());
    }

    @Override
    public void reset() {
        this.operationCounter = 0;
    }
}
