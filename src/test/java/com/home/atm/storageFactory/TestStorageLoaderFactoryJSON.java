package com.home.atm.storageFactory;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoaderFactory;
import com.home.atm.storage.StorageLoaderJSON;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.IOException;
import static com.home.atm.storage.StorageConstant.STORAGE_TYPE;

public class TestStorageLoaderFactoryJSON {

    private StorageLoaderFactory storageLoaderFactory;
    private StorageLoaderJSON storageLoaderJSON = Mockito.mock(StorageLoaderJSON.class);

    @Before
    public void init() {
        storageLoaderFactory = new StorageLoaderFactory();
        storageLoaderFactory.setStorageLoaderJSON(storageLoaderJSON);
    }

    static{
       System.setProperty(STORAGE_TYPE, "json");
    }

    @Test
    public void testStorageLoaderJSON() throws IOException{
        Mockito.when(storageLoaderJSON.loadStorage()).thenReturn(new Storage());
        storageLoaderFactory.loadStorage();
        Mockito.verify(storageLoaderJSON, Mockito.times(1)).loadStorage();
    }
}
