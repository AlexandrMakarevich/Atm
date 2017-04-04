package com.home.atm.storage;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class StorageSaverJSON implements StorageSaver {

    private ObjectMapper mapper = new ObjectMapper();
    private String fileName;

    public StorageSaverJSON(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeData(Storage storage) throws IOException {
        File file = new File(fileName);
        mapper.writeValue(file, storage.getAccountStorage());
    }
}
