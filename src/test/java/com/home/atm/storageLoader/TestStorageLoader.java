package com.home.atm.storageLoader;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoader;
import org.junit.Assert;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class TestStorageLoader {
    //TODO: use @Before
    StorageLoader storageLoader = new StorageLoader();

    @Test
    public void testLoadStorage() throws FileNotFoundException {
        Map<String, Integer> map = new HashMap<>();
        map.put("RUB",84844 );
        map.put("USD", 100);
        map.put("EUR", 300);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("RUB1", 848);
        map2.put("USD1", 200);
        Map<String, Map<String, Integer>> map1 = new HashMap<>();
        map1.put("Petrov", map);
        map1.put("Petrov2",map2);
        Storage actualResult = storageLoader.loadStorage("src/test/resources/Storage.txt");
        Assert.assertEquals("ActualResult must be expected", map1, actualResult.getAccountStorage());
    }
}
