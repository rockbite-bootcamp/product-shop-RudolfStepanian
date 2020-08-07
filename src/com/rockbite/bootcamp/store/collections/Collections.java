package com.rockbite.bootcamp.store.collections;

import java.util.HashMap;

public abstract class Collections<T>{
    protected HashMap<T,Integer> collection = new HashMap<>();

    /**
     * get function for collection
     * @return
     */
    public HashMap<T,Integer> getCollection() {
        return this.collection;
    }


    /**
     * adding element to collection
     * @param product
     * @param count
     */
    public void addItem(T product, int count) {
        if (collection.get(product) == null){
            collection.put(product, count);
        } else {
            collection.put(product, count + collection.get(product));
        }
    }

    /**
     * removing element from collection
     * @param curr
     * @param count
     */
    public void removeItem(T curr, int count) {
        if (!(collection.get(curr) == null || collection.get(curr)<count)){
            collection.put(curr, collection.get(curr) - count);
        }
    }

    /**
     * get count of selected element
     * @param curr
     * @return
     */
    public int getCount(T curr) {
        if (collection.containsKey(curr)){
            return collection.get(curr);
        }
        return 0;
    }
}
