package com.home.atm.command;

import com.home.atm.storage.Storage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class ExitCommand implements Command {


    @Override
    public void execute(Storage storage) {
       try{
           writeData(storage);
       }
       catch(FileNotFoundException ex) {
           System.out.println("Щшибка сохранения файла " + ex.getMessage());
       }
        System.out.println("Получил команду выйти.");
        System.exit(0);

    }

    public void writeData(Storage storage) throws FileNotFoundException {
        PrintWriter pwt = new PrintWriter("Storage.txt");
        for (Map.Entry<String, Integer> entry : storage.getStorage().entrySet()) {
            pwt.format("%s %d\n", entry.getKey(), entry.getValue());
        }
        pwt.flush();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
