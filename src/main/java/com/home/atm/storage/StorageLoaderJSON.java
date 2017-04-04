package com.home.atm.storage;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class StorageLoaderJSON implements StorageLoader {

    private ObjectMapper mapper = new ObjectMapper();
    private String fileName;

    public StorageLoaderJSON(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Storage loadStorage() throws IOException {
        File file = new File(fileName);
        Map<String, Map<String, Integer>> accountStorage = mapper.readValue(file, Map.class );
        return new Storage(accountStorage);
    }
}
