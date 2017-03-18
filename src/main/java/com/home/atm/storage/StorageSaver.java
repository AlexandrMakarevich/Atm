package com.home.atm.storage;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class StorageSaver {

   public static final String FILENAME = "Storage.txt";
    private static final Logger LOGGER = Logger.getLogger(StorageSaver.class);

    public void writeData(Storage storage) throws FileNotFoundException {
        File file  = new File(FILENAME);
        PrintWriter pwt = new PrintWriter(file);
        for (Map.Entry<String, Integer> entry : storage.getStorage().entrySet()) {
            pwt.format("%s %d\n", entry.getKey(), entry.getValue());
        }
        pwt.flush();
        LOGGER.info(storage.getStorage() + " Данные сохранены в файл " + file.getAbsolutePath());
    }
}
