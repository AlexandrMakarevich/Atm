package com.home.atm;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoader;
import java.io.FileNotFoundException;

public class AtmLauncher {

    private static StorageLoader storageLoader = new StorageLoader();

    public static void main(String[] args) throws FileNotFoundException {
        Storage stor = storageLoader.loadStorage(args[0]);
        Service service = new Service(stor);
        for (; ; ) {
            try {
                service.procesInput();
            } catch (IllegalArgumentException ex) {
                System.out.println("Произошла ошибка. " + ex.getMessage());
            }
        }

    }
}
