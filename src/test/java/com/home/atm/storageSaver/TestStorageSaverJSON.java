package com.home.atm.storageSaver;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoaderJSON;
import com.home.atm.storage.StorageSaverJSON;
import com.home.atm.storageLoader.TestStorageLoaderJSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class TestStorageSaverJSON {

    private StorageSaverJSON storageSaverJSON;
    private TestStorageLoaderJSON testStorageLoaderJSON;
    private StorageLoaderJSON storageLoaderJSON;

    @Before
    public void init() {
        storageSaverJSON = new StorageSaverJSON();
        testStorageLoaderJSON = new TestStorageLoaderJSON();
        storageLoaderJSON = new StorageLoaderJSON();
    }

    @Test
    public void testStorageSaverJSON() throws IOException {
        Storage actualStorage = testStorageLoaderJSON.createStorage();
        storageSaverJSON.writer(actualStorage, "target/Storage_test.json");
        Storage expectedStorage = storageLoaderJSON.loadStorage("target/Storage_test.json");
        Assert.assertEquals("Actual result must be expected", expectedStorage.getAccountStorage(), actualStorage.getAccountStorage());
    }
}
