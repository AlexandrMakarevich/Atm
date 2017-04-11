package com.home.atm.storageFactory;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoaderCSV;
import com.home.atm.storage.StorageLoaderFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.io.IOException;
import static com.home.atm.storage.StorageConstant.STORAGE_TYPE;

public class TestStorageLoaderFactoryCSV {

    private StorageLoaderFactory storageLoaderFactory;

    @Mock
    private StorageLoaderCSV storageLoaderCSV = Mockito.mock(StorageLoaderCSV.class);

    @Before
    public void init() {
        storageLoaderFactory = new StorageLoaderFactory();
        storageLoaderFactory.setStorageLoaderCSV(storageLoaderCSV);
    }

    @Test
    public void testLoadCSV() throws IOException{
        Mockito.when(storageLoaderCSV.loadStorage()).thenReturn(new Storage());
        storageLoaderFactory.loadStorage();
        Mockito.verify(storageLoaderCSV , Mockito.times(1)).loadStorage();
    }
    static{
        System.setProperty(STORAGE_TYPE, "csv");
    }
}
