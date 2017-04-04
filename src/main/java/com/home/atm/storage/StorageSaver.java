package com.home.atm.storage;

import java.io.IOException;

public interface StorageSaver {

    void writeData(Storage storage) throws IOException;
}
