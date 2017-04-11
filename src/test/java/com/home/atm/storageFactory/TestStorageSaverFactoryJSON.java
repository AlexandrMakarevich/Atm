package com.home.atm.storageFactory;

import com.home.atm.storage.Storage;;
import com.home.atm.storage.StorageSaverFactory;
import com.home.atm.storage.StorageSaverJSON;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.io.IOException;
import static com.home.atm.storage.StorageConstant.STORAGE_TYPE;

public class TestStorageSaverFactoryJSON {

    private StorageSaverFactory storageSaverFactory;
    private Storage storage;

    @Mock
    private StorageSaverJSON storageSaverJSON = Mockito.mock(StorageSaverJSON.class);

    @Before
    public void setUp() {
        // MockitoAnnotations.initMocks(this);
        storage = new Storage();
        storageSaverFactory = new StorageSaverFactory();
        storageSaverFactory.setStorageSaverJSON(storageSaverJSON);
    }

    static {
        System.setProperty(STORAGE_TYPE, "json");
    }

    @Test
    public void testJsonSaver() throws IOException {
        Mockito.doNothing().when(storageSaverJSON).writeData(storage);
        storageSaverFactory.writeData(storage);
        Mockito.verify(storageSaverJSON, Mockito.times(1)).writeData(storage);
    }
}

