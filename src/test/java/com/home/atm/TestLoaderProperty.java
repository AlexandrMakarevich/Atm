package com.home.atm;

import com.home.atm.storage.LoaderProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class TestLoaderProperty {

    private LoaderProperty loaderProperty;
    private String expectedStorageType = "json";
    private String expectedStorageFile = "Storage.json";

    @Before
    public void init()  {
       loaderProperty = new LoaderProperty();
    }

    @Test
    public void testLoadStorageType() throws IOException {
        loaderProperty.loadProperty();
        Assert.assertEquals("Actual result must be expected", expectedStorageType, System.getProperty("storageType"));
        Assert.assertEquals("Actual result must be expected", expectedStorageFile, System.getProperty("storageFile"));
    }
}
