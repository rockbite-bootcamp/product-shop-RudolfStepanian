package com.rockbite.bootcamp.store;

import com.rockbite.bootcamp.store.Shop.IncrementCommand;
import com.rockbite.bootcamp.store.collections.Resources.Item;

public interface IInventory {
    void setLastTransaction(IncrementCommand command);
    IncrementCommand getLastTransaction();

    boolean hasItem(Item item, int count);

    void spendItem(Item item, int count);

    void addItem(Item item, int count);
}
