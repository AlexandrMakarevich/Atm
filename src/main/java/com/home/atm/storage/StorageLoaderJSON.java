package com.home.atm.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class StorageLoaderJSON implements StorageLoader {

    private ObjectMapper mapper = new ObjectMapper();
    private String fileName;
    private static final Logger LOGGER = Logger.getLogger(StorageLoaderJSON.class);

    public StorageLoaderJSON(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Storage loadStorage() throws IOException {
        File file = new File(fileName);
        Storage storage = mapper.readValue(file, Storage.class);
        LOGGER.info("Loaded from " + file.getAbsolutePath() + " debitStorage " +
                storage.getAccountStorage() + " creditStorage " + storage.getCredit());
        return storage;
    }
}
