package com.home.atm;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoader;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;

public class PreparationAtm {

    private static StorageLoader storageLoader = new StorageLoader();
    private static final Logger LOGGER = Logger.getLogger(AtmLauncher.class);

    public void argumentsRun(String args) throws FileNotFoundException {
        Storage storage = storageLoader.loadStorage(args);
        process(storage);
    }

    public void createNewAccount() {
        Storage storage = new Storage();
        process(storage);
    }

    public void process(Storage storage) {
        Service service = new Service(storage);
        for (; ; ) {
            try {
                service.procesInput();
            } catch (IllegalArgumentException ex) {
                System.out.println("��������� ������. " + ex.getMessage());
                LOGGER.warn("��������� ������. ", ex);
            }
        }
    }
}
