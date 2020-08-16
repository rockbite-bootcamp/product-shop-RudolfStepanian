package com.rockbite.bootcamp.store.Pool;

import java.util.ArrayList;

public abstract class Pool<T extends IPool> {
    public ArrayList<T> freeObjects = new ArrayList<>();
    public ArrayList<T> usedObjects = new ArrayList<>();

    protected abstract T newObject();

    public T obtain(){
        if (freeObjects.isEmpty()){
            T object = newObject();
            freeObjects.add(object);
        }
        T object = freeObjects.remove(0);
        usedObjects.add(object);
        return object;
    }

    public T obtain(T object){
        usedObjects.add(object);
        return object;
    }

    public void free(T object){
        usedObjects.remove(object);
        freeObjects.add(object);

        object.reset();
    }

    @Override
    public String toString() {
        return "freeObjects=" + freeObjects + "\n" +
               "usedObjects=" + usedObjects + "\n";
    }
}
