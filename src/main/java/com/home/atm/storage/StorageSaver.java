package com.home.atm.storage;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import static com.home.atm.storage.StorageLoader.ACCOUNT_DIRECTORY;

public class StorageSaver {
    public static String filename = "Save_account.txt";
    private static final Logger LOGGER = Logger.getLogger(StorageSaver.class);

    public void writeData(Storage storage) throws FileNotFoundException {
        File file  = new File(ACCOUNT_DIRECTORY, filename);
        PrintWriter pwt = new PrintWriter(file);
        for (Map.Entry<String, Integer> entry : storage.getStorage().entrySet()) {
            pwt.format("%s %d\n", entry.getKey(), entry.getValue());
        }
        pwt.flush();
        LOGGER.info(storage.getStorage() + " Данные сохранены в файл " + file.getAbsolutePath());
    }
}
