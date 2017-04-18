package com.home.atm.command;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageSaverFactory;
import org.apache.log4j.Logger;
import java.io.IOException;

public class ExitCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ExitCommand.class);
    private StorageSaverFactory storageSaverFactory;

    public ExitCommand() {
       storageSaverFactory = new StorageSaverFactory();
    }

    @Override
    public void execute(Storage storage) {
       try{
           storageSaverFactory.writeData(storage);
       }
       catch(IOException ex) {
           System.out.println("File saving error " + ex.getMessage());
           LOGGER.warn("File saving error ", ex);
       }
        System.out.println("Get command close.");
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
