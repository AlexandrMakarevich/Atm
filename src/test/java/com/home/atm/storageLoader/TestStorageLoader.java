package com.home.atm.storageLoader;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoaderCSV;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

public class TestStorageLoader {

    private StorageLoaderCSV storageLoader;

    @Before
    public void init() {
        storageLoader = new StorageLoaderCSV("src/test/resources/Storage.txt");
    }

    @Test
    public void testLoadStorage() throws FileNotFoundException {
        Storage actualResult = storageLoader.loadStorage();
        Assert.assertEquals("ActualResult must be expected", createStorage().getAccountStorage(), actualResult.getAccountStorage());
    }

    public Storage createStorage() {
        Map<String, Integer> petrov = new TreeMap<>();
        petrov.put("RUB", 84844);
        petrov.put("USD", 100);
        petrov.put("EUR", 300);
        Map<String, Integer> petrov2 = new TreeMap<>();
        petrov2.put("RUB1", 848);
        petrov2.put("USD1", 200);
        Map<String, Map<String, Integer>> account = new TreeMap<>();
        account.put("Petrov", petrov);
        account.put("Petrov2", petrov2);
        return new Storage(account);
    }
}
