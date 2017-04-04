package com.home.atm.storage;

import java.io.IOException;

public interface StorageLoader {

    Storage loadStorage() throws IOException;
}
