package com.home.atm;

import com.home.atm.Service;
import com.home.atm.storage.Storage;

public class AtmLauncher {
    public static void main (String[] args) {
        Storage storage = new Storage();
        Service service = new Service(storage);
        service.procesInput();

    }
}
