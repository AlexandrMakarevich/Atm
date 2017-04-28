package com.home.atm.storage;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class StorageSaverCSV implements StorageSaver {

    public static String fileName;
    private static final Logger LOGGER = Logger.getLogger(StorageSaverCSV.class);

    public StorageSaverCSV(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeData(Storage storage) throws IOException {
        File file = new File(fileName);
        PrintWriter pwt = new PrintWriter(file);
        readStorage(storage.getAccountStorage(), "balance", pwt);
        readStorage(storage.getCredit(), "credit", pwt);
        LOGGER.info(storage.getAccountStorage() + " Data save in file " + file.getAbsolutePath());
    }

    public void readStorage(Map<String, Map<String, Integer>> storage, String storageName, PrintWriter pwt) {
        for (Map.Entry<String, Map<String, Integer>> map : storage.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(storageName)
                    .append(" ")
                    .append(map.getKey());
            readAccount(map, stringBuilder);
            stringBuilder.append("\n");
            pwt.format(stringBuilder.toString());
        }
        pwt.flush();
    }

    public void readAccount(Map.Entry<String, Map<String, Integer>> map, StringBuilder stringBuilder) {
        for (Map.Entry<String, Integer> entry : map.getValue().entrySet()) {
            stringBuilder.append(" ")
                    .append(entry.getKey())
                    .append(" ")
                    .append(entry.getValue());
        }
    }
}
