package com.rockbite.bootcamp.store.Command;

import com.rockbite.bootcamp.store.Pool.IPool;

public interface IRedoUndo extends IPool {

    void execute();

    void undo();
}
