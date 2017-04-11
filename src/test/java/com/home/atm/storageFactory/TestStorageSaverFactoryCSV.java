package com.home.atm.storageFactory;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageSaverCSV;
import com.home.atm.storage.StorageSaverFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.io.IOException;
import static com.home.atm.storage.StorageConstant.STORAGE_TYPE;

public class TestStorageSaverFactoryCSV {

    private StorageSaverFactory storageSaverFactory;
    private Storage storage;

    @Mock
    private StorageSaverCSV storageSaverCSV = Mockito.mock(StorageSaverCSV.class);

    @Before
    public void init() {
        storage = new Storage();
        storageSaverFactory = new StorageSaverFactory();
        storageSaverFactory.setStorageSaverCSV(storageSaverCSV);
    }

    static {
        System.setProperty(STORAGE_TYPE, "csv");
    }

    @Test
    public void testLoadCSV() throws IOException {
        Mockito.doNothing().when(storageSaverCSV).writeData(storage);
        storageSaverFactory.writeData(storage);
        Mockito.verify(storageSaverCSV, Mockito.times(1)).writeData(storage);
    }
}
