package com.rockbite.bootcamp.store.Singleton;

public class PurchaseCount {
    private static PurchaseCount instance;

    private int count = 0;

    private PurchaseCount() {}

    public static PurchaseCount getInstance() {
        if (instance == null) {
            instance = new PurchaseCount();
        }
        return instance;
    }

    public void increment() {
        count++;
    }
    public void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
