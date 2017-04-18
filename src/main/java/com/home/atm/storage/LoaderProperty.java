package com.home.atm.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoaderProperty {

    public void loadProperty() throws IOException{
        Properties properties = System.getProperties();
        InputStream inputStream = getClass().getResourceAsStream("/atm.properties");
        properties.load(inputStream);
        System.setProperties(properties);
        inputStream.close();
    }
}
