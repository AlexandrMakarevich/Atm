package com.home.atm.storage;

public class StorageConstant {

    private final String JSON_FORMAT = "json";
    private final String CSV_FORMAT = "csv";
    public static final String STORAGE_TYPE = "storageType";
    public static final String STORAGE_TYPE_PROPERTY = System.getProperty(STORAGE_TYPE);

    public boolean isJSON() {
        return JSON_FORMAT.equals(STORAGE_TYPE_PROPERTY);
    }

    public boolean isCSV() {
        return CSV_FORMAT.equals(STORAGE_TYPE_PROPERTY);
    }
}
