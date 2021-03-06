package com.home.atm.storage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class StorageSaverJSON implements StorageSaver {

    private ObjectMapper mapper = new ObjectMapper();
    private String fileName;
    private static final Logger LOGGER = Logger.getLogger(StorageSaverJSON.class);

    public StorageSaverJSON(String fileName) {
        this.fileName = fileName;
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.disable(MapperFeature.AUTO_DETECT_GETTERS);
    }

    @Override
    public void writeData(Storage storage) throws IOException {
        File file = new File(fileName);
        mapper.writeValue(file, storage);
        LOGGER.info("Data save in file " + file.getAbsolutePath() + " debitStorage " +
                storage.getAccountStorage() + " creditStorage " + storage.getCredit());
    }
}
