package com.home.atm.storageLoader;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoaderJSON;
import com.home.atm.storage.StorageSaverJSON;
import com.home.atm.storageSaver.TestStorageSaver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestStorageLoaderJSON {

    private String fileActualStorage = "src/test/resources/Storage_test.json";
    private StorageLoaderJSON storageLoaderJSON;

    @Before
    public void init() {
        storageLoaderJSON = new StorageLoaderJSON(fileActualStorage);
    }

    @Test
    public void testStorageLoaderJSON()throws IOException {
        Storage actualStorage = storageLoaderJSON.loadStorage();
        Storage expectedStorage = createStorage();
        Assert.assertEquals("Actual result must be expected",  expectedStorage.getAccountStorage(), actualStorage.getAccountStorage());
        System.out.println(actualStorage.getAccountStorage());
    }

    public Storage createStorage() {
        Map<String, Integer> petrov = new HashMap<>();
        petrov.put("RUB", 84844);
        petrov.put("USD", 100);
        petrov.put("EUR", 300);
        Map<String, Integer> petrov2 = new HashMap<>();
        petrov2.put("RUB1", 848);
        petrov2.put("USD1", 200);
        Map<String, Map<String, Integer>> account = new HashMap<>();
        account.put("Petrov", petrov);
        account.put("Petrov2", petrov2);
        Map<String, Integer> petrov3 = new HashMap<>();
        petrov3.put("USD", 100);
        Map<String, Map<String, Integer>> credit = new HashMap<>();
        credit.put("Petrov", petrov3);
        return new Storage(account, credit);
    }
}
