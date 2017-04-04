package com.home.atm.storage;

import java.io.IOException;

public class StorageSaverFactory implements StorageSaver {

    private StorageSaverJSON storageSaverJSON = new StorageSaverJSON(StorageLoaderFactory.JSON_STORAGE);
    private StorageSaverCSV storageSaverCSV = new StorageSaverCSV(StorageLoaderFactory.CSV_STORAGE);
    private StorageConstant storageConstant = new StorageConstant();

    @Override
    public void writeData(Storage storage) throws IOException {
        if (storageConstant.isJSON()) {
            storageSaverJSON.writeData(storage);
            return;
        }
        if (storageConstant.isCSV()) {
            storageSaverCSV.writeData(storage);
            return;
        }
        throw new IllegalArgumentException("Wrong format of STORAGE_TYPE,it must be csv or json");
    }
}
