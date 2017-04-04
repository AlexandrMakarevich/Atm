package com.home.atm;

import com.home.atm.command.ServiceAccount;
import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoaderJSON;
import java.io.IOException;

public class AtmLauncher {

    public static void main(String[] args) throws IOException{
//        StorageLoader storageLoader = new StorageLoader();
//        Storage storage = storageLoader.loadStorage("Save_account.txt");
        StorageLoaderJSON storageLoaderJSON = new StorageLoaderJSON();
        Storage storage = storageLoaderJSON.loadStorage("src/main/resources/Storage.json");
        ServiceAccount serviceAccount = new ServiceAccount();
        serviceAccount.launchAccount(storage);
    }
}
