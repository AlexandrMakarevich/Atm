package com.home.atm.storage;

import java.io.IOException;

public class StorageLoaderFactory implements StorageLoader {

    public static final String JSON_STORAGE = "Storage.json";
    public static final String CSV_STORAGE = "Storage.csv";
    private StorageLoaderCSV storageLoaderCSV = new StorageLoaderCSV(CSV_STORAGE);
    private StorageLoaderJSON storageLoaderJSON = new StorageLoaderJSON(JSON_STORAGE);
    private StorageConstant storageConstant = new StorageConstant();

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
