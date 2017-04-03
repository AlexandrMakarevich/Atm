package com.home.atm;

import com.home.atm.command.ServiceAccount;
import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoader;
import java.io.FileNotFoundException;

public class AtmLauncher {

    public static void main(String[] args) throws FileNotFoundException {
        StorageLoader storageLoader = new StorageLoader();
        Storage storage = storageLoader.loadStorage("Save_account.txt");
        ServiceAccount serviceAccount = new ServiceAccount();
        serviceAccount.launchAccount(storage);
    }
}
