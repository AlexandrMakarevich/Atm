package com.home.atm.command;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageSaver;
import com.home.atm.storage.StorageSaverJSON;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExitCommand implements Command {

    private StorageSaver storageSever ;
    private static final Logger LOGGER = Logger.getLogger(ExitCommand.class);
    private StorageSaverJSON storageSaverJSON = new StorageSaverJSON();

    public ExitCommand() {
       storageSever = new StorageSaver();
    }

    @Override
    public void execute(Storage storage) {
       try{
           storageSaverJSON.writer(storage, "src/main/resources/Storage.json");
       }
       catch(IOException ex) {
           System.out.println("Ошибка сохранения файла " + ex.getMessage());
           LOGGER.warn("Ошибка сохранения файла ", ex);
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
