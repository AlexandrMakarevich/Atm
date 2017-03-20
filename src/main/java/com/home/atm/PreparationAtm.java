package com.home.atm;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoader;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;

public class PreparationAtm {

    private StorageLoader storageLoader = new StorageLoader();
    private static final Logger LOGGER = Logger.getLogger(AtmLauncher.class);
    private static final String DEFAULT_FILE_NAME = "Default.txt";

    public void argumentsRun(String args) throws FileNotFoundException {
         Storage stor = storageLoader.loadStorage(args);
       process(stor, args);
    }

    public void createNewAccount() {
        Storage storage = new Storage();
       process(storage, DEFAULT_FILE_NAME);
    }

    private void process(Storage stor, String filename) {
        Service service = new Service(stor, filename);
        for (; ; ) {
            try {
                service.procesInput();
            } catch (IllegalArgumentException ex) {
                System.out.println("Произошла ошибка. " + ex.getMessage());
                LOGGER.warn("Произошла ошибка. ", ex);
            }
        }
    }
}
