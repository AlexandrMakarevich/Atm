package com.home.atm.storage;

public class StorageConstant {

    private final String JSON_FORMAT = "json";
    private final String CSV_FORMAT = "csv";
    public static final String STORAGE_TYPE = System.getProperty("storageType");

    public boolean isJSON() {
        return JSON_FORMAT.equals(STORAGE_TYPE);
    }

    public boolean isCSV() {
        return CSV_FORMAT.equals(STORAGE_TYPE);
    }
}
