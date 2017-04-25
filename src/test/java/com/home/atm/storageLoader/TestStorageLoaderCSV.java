package com.home.atm.storageLoader;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoaderCSV;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

public class TestStorageLoaderCSV {

    private StorageLoaderCSV storageLoader;

    @Before
    public void init() {
        storageLoader = new StorageLoaderCSV("src/test/resources/Storage.txt");
    }

    @Test
    public void testLoadStorage() throws FileNotFoundException {
        Storage actualResult = storageLoader.loadStorage();
        Assert.assertEquals("ActualResult must be expected", createStorage(), actualResult);
    }

    public Storage createStorage() {
        Map<String, Integer> balance = new TreeMap<>();
        balance.put("RUB", 84844);
        balance.put("USD", 100);
        balance.put("EUR", 300);
        Map<String, Integer> balance2 = new TreeMap<>();
        balance2.put("RUB1", 848);
        balance2.put("USD1", 200);
        Map<String, Map<String, Integer>> account = new TreeMap<>();
        account.put("Petrov", balance);
        account.put("Petrov2", balance2);
        Map<String, Integer> credit = new TreeMap<>();
        credit.put("RUB1", 848);
        credit.put("USD1", 200);
        Map<String, Integer> credit2 = new TreeMap<>();
        credit2.put("RUB1", 848);
        credit2.put("USD1", 200);
        Map<String, Map<String, Integer>> creditAccount = new TreeMap<>();
        creditAccount.put("Petrov2", credit);
        creditAccount.put("Petrov", credit2);
        return new Storage(account, creditAccount);
    }
}
