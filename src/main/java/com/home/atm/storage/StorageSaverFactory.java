package com.home.atm.storage;

import java.io.IOException;

public class StorageSaverFactory implements StorageSaver {

    private StorageConstant storageConstant = new StorageConstant();
    private String storageNameFile = storageConstant.getFileNameProperty();
    private StorageSaverJSON storageSaverJSON = new StorageSaverJSON(storageNameFile);
    private StorageSaverCSV storageSaverCSV = new StorageSaverCSV(storageNameFile);


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

    public void setStorageSaverJSON(StorageSaverJSON storageSaverJSON) {
        this.storageSaverJSON = storageSaverJSON;
    }

    public void setStorageSaverCSV(StorageSaverCSV storageSaverCSV) {
        this.storageSaverCSV = storageSaverCSV;
    }
}
