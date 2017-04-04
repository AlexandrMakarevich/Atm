package com.home.atm.storage;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class StorageLoaderJSON {

    private ObjectMapper mapper = new ObjectMapper();

    public Storage loadStorage(String fileName) throws IOException {
        File file = new File(fileName);
        Map<String, Map<String, Integer>> accountStorage = mapper.readValue(file, Map.class );
        return new Storage(accountStorage);
    }
}
