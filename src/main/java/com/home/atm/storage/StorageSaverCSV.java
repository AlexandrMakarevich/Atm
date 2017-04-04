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
        for (Map.Entry<String, Map<String, Integer>> map : storage.getAccountStorage().entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(map.getKey());
            for (Map.Entry<String, Integer> entry : map.getValue().entrySet()) {
                stringBuilder.append(" ")
                        .append(entry.getKey())
                        .append(" ")
                        .append(entry.getValue());
            }
            stringBuilder.append("\n");
            pwt.format(stringBuilder.toString());
        }
        pwt.flush();
        LOGGER.info(storage.getAccountStorage() + " Данные сохранены в файл " + file.getAbsolutePath());
    }
}
