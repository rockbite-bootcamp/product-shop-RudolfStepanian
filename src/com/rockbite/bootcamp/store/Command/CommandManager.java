package com.rockbite.bootcamp.store.Command;

import java.util.ArrayList;

public class CommandManager {
    ArrayList<IRedoUndo> history = new ArrayList<>();
    private int cursor = 0;

    public void executeCommand(IRedoUndo command){
        command.execute();

        if (cursor < history.size()){
            history.set(cursor, command);
        } else {
            history.add(command);
        }

        cursor++;
    }

    public void undo(){
        if(cursor == 0){
            return;
        }
        IRedoUndo command = history.get(cursor-1);
        command.undo();

        cursor--;
    }

    public void redo(){
        if (cursor >= history.size()){
            return;
        }
        IRedoUndo command = history.get(cursor-1);
        command.execute();
        cursor++;
    }
}
