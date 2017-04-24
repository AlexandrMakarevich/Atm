package com.home.atm;

import com.home.atm.storage.Storage;
import org.apache.log4j.Logger;

public class PreparationAtm {

    private static final Logger LOGGER = Logger.getLogger(PreparationAtm.class);

    public void process(Storage storage) {
        Service service = new Service(storage);
        for (; ; ) {
            try {
                service.procesInput();
            } catch (IllegalArgumentException ex) {
                System.out.println("An error has occurred. " + ex.getMessage());
                LOGGER.warn("An error has occurred. ", ex);
            }
        }
    }
}
