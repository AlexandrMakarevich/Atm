package com.home.atm.storage;

import java.io.IOException;

public class StorageLoaderFactory implements StorageLoader {

    private StorageConstant storageConstant = new StorageConstant();
    private String storageNameFile = storageConstant.getFileNameProperty();
    private StorageLoaderCSV storageLoaderCSV = new StorageLoaderCSV(storageNameFile);
    private StorageLoaderJSON storageLoaderJSON = new StorageLoaderJSON(storageNameFile);

    @Override
    public Storage loadStorage() throws IOException {
        if (storageConstant.isJSON()) {
           return storageLoaderJSON.loadStorage();
        }
        if (storageConstant.isCSV()) {
            return storageLoaderCSV.loadStorage();
        }
        throw new IllegalArgumentException("Wrong value of STORAGE_TYPE,allowed values csv, json");
    }

    public void setStorageLoaderCSV(StorageLoaderCSV storageLoaderCSV) {
        this.storageLoaderCSV = storageLoaderCSV;
    }

    public void setStorageLoaderJSON(StorageLoaderJSON storageLoaderJSON) {
        this.storageLoaderJSON = storageLoaderJSON;
    }
}
