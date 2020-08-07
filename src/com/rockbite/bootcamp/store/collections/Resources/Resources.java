package com.rockbite.bootcamp.store.collections.Resources;

import com.rockbite.bootcamp.store.collections.Collections;

public class Resources extends Collections<Item> {

    /**
     * demonstration function
     * @return
     */
    @Override
    public String toString() {
        String string = "";
        for(Item type: collection.keySet()){
//            string += type.getType() + ": " + getCount(type) + "\n";
        }
        return string;
    }
}
