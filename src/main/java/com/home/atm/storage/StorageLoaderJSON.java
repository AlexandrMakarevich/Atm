package com.home.atm.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class StorageLoaderJSON implements StorageLoader {

    private ObjectMapper mapper = new ObjectMapper();
    private String fileName;

    public StorageLoaderJSON(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Storage loadStorage() throws IOException {
        File file = new File(fileName);
        Storage storage = mapper.readValue(file, Storage.class);
        return storage;
    }
}
