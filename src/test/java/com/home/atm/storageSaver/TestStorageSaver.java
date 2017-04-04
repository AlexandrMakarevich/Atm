package com.home.atm.storageSaver;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageSaverCSV;
import com.home.atm.storageLoader.TestStorageLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TestStorageSaver {

    private StorageSaverCSV storageSaver;
    private TestStorageLoader testStorageLoader;
    private String expectedFile = "src/test/resources/Storage.txt";
    public static String file = "target/Save_account.txt";

    @Before
    public void init() {
        storageSaver = new StorageSaverCSV("AA");
        testStorageLoader = new TestStorageLoader();
        StorageSaverCSV.fileName = file;
    }

    @Test
    public void testStorageSaver() throws IOException{
        Storage storage = testStorageLoader.createStorage();
        storageSaver.writeData(storage);
        List<String> actualList = loadFile(file);
        List<String> expectedList = loadFile(expectedFile);
        Assert.assertEquals("Actual result must be expected", expectedList, actualList);
    }

    public List<String> loadFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        return list;
    }
}
