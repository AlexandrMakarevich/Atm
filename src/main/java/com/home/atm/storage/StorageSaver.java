package com.home.atm.storage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class StorageSaver {

   public static final String FILENAME = "Storage.txt";

    public void writeData(Storage storage) throws FileNotFoundException {
        PrintWriter pwt = new PrintWriter(FILENAME);
        for (Map.Entry<String, Integer> entry : storage.getStorage().entrySet()) {
            pwt.format("%s %d\n", entry.getKey(), entry.getValue());
        }
        pwt.flush();
    }
}
