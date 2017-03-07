package com.home.atm.command;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageSaver;
import java.io.FileNotFoundException;

public class ExitCommand implements Command {

    private StorageSaver storageSever = new StorageSaver();

    @Override
    public void execute(Storage storage) {
       try{
           storageSever.writeData(storage);
       }
       catch(FileNotFoundException ex) {
           System.out.println("Ошибка сохранения файла " + ex.getMessage());
       }
        System.out.println("Получил команду выйти.");
        System.exit(0);
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
