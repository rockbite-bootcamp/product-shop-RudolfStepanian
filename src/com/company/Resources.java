package com.company;

import java.util.HashMap;

public class Resources extends Collections<Currency> {

    /**
     * demonstration function
     * @return
     */
    @Override
    public String toString() {
        String string = "";
        for(Currency type: collection.keySet()){
            string += type.getName() + ": " + getCount(type) + "\n";
        }
        return string;
    }
}
