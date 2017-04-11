package com.home.atm.storage;

public class StorageConstant {

    private final String JSON_FORMAT = "json";
    private final String CSV_FORMAT = "csv";
    public static final String STORAGE_TYPE = "storageType";

    public boolean isJSON() {
        return JSON_FORMAT.equals(getStorageTypeProperty());
    }

    public boolean isCSV() {
        return CSV_FORMAT.equals(getStorageTypeProperty());
    }

    private String getStorageTypeProperty() {
        return System.getProperty(STORAGE_TYPE);
    }

}
