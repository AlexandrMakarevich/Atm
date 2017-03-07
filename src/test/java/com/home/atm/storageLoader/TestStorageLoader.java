package com.home.atm.storageLoader;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoader;
import org.junit.Assert;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class TestStorageLoader {

    StorageLoader storageLoader = new StorageLoader();

    @Test
    public void testLoadStorage() throws FileNotFoundException {
        Map<String, Integer> map = new HashMap<>();
        map.put("RUB",84844 );
        map.put("USD", 435);
        Storage actualResult = storageLoader.loadStorage("src/test/resources/Storage.txt");
        Assert.assertEquals("ActualResult must be expected", map, actualResult.getStorage());
    }
}
