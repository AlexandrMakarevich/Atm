package com.home.atm;

import com.home.atm.command.ServiceAccount;
import com.home.atm.storage.LoaderProperty;
import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoaderFactory;
import java.io.IOException;

public class AtmLauncher {

    public static void main(String[] args) throws IOException{
        LoaderProperty loaderProperty = new LoaderProperty();
        loaderProperty.loadProperty();
        StorageLoaderFactory storageLoaderFactory = new StorageLoaderFactory();
        Storage storage = storageLoaderFactory.loadStorage();
        ServiceAccount serviceAccount = new ServiceAccount();
        serviceAccount.launchAccount(storage);
    }
}
