package com.rockbite.bootcamp.store.collections.Resources;

import com.rockbite.bootcamp.store.collections.ICollectionItem;

public class Item implements ICollectionItem {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    public ItemTypes getType() {
//        return type;
//    }
//    public Item(ItemTypes type){
//        this.type = type;
//    }

    @Override
    public String toString() {
        return name;
    }
}

