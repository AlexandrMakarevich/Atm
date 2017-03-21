package com.home.atm;

import java.io.FileNotFoundException;
import static com.home.atm.storage.StorageSaver.filename;

public class AtmLauncher {

    public static void main(String[] args) throws FileNotFoundException {
        PreparationAtm preparationAtm = new PreparationAtm();
        if (args.length > 0) {
            filename = args[0];
            preparationAtm.argumentsRun(args[0]);
        }
        else {
            preparationAtm.createNewAccount();
        }
    }
}
