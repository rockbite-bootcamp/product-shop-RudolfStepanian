package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage extends Collections<Product> {

    /**
     * demonstration function
     * @return
     */
    @Override
    public String toString() {
        String string = "";
        for(Product type: collection.keySet()){
            string += type.getName() + ": " + getCount(type) + "\n";
        }
        return string;
    }
}
