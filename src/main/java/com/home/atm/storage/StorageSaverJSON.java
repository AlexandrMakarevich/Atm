package com.home.atm.storage;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class StorageSaverJSON {

    private ObjectMapper mapper = new ObjectMapper();

    public void writer(Storage storage, String fileName) throws IOException {
        File file = new File(fileName);
        mapper.writeValue(file, storage.getAccountStorage());
    }
}
