package com.home.atm;

import com.home.atm.storage.Storage;

public class AtmLauncher {

    public static void main(String[] args) {
        Storage storage = new Storage();
        Service service = new Service(storage);
        for (; ; ) {
            try {
                service.procesInput();
            } catch (IllegalArgumentException ex) {
                System.out.println("Произошла ошибка. " + ex.getMessage());
            }
        }

    }
}
