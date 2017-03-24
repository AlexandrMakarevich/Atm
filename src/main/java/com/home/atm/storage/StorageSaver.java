package com.home.atm.storage;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class StorageSaver {

    public static String filename = "Save_account.txt";
    private static final Logger LOGGER = Logger.getLogger(StorageSaver.class);

    public void writeData(Storage storage) throws FileNotFoundException {
        File file = new File(filename);
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
