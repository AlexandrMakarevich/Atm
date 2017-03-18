package com.home.atm;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoader;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;

public class PreparationAtm {

    private static StorageLoader storageLoader = new StorageLoader();
    private static final Logger LOGGER = Logger.getLogger(AtmLauncher.class);

    public void argumentsRun(String args) throws FileNotFoundException {
        Storage stor = storageLoader.loadStorage(args);
        Service service = new Service(stor);
        for (; ; ) {
            try {
                service.procesInput();
            } catch (IllegalArgumentException ex) {
                System.out.println("Произошла ошибка. " + ex.getMessage());
                LOGGER.warn("Произошла ошибка. ", ex);
            }
        }
    }

    public void createNewAccount() {
        Storage storage = new Storage();
        Service service = new Service(storage);
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
