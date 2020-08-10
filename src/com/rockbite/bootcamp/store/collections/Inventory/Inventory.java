package com.rockbite.bootcamp.store.collections.Inventory;

import com.rockbite.bootcamp.store.Product;
import com.rockbite.bootcamp.store.collections.Collections;

public class Inventory extends Collections<Product> {

    /**
     * demonstration function
     * @return
     */
    @Override
    public String toString() {
        String string = "";
        for(Product type: collection.keySet()){
            string += type.toString() + ": " + getCount(type) + "\n";
        }
        return string;
    }

}
