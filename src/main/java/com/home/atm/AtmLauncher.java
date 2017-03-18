package com.home.atm;

import java.io.FileNotFoundException;

public class AtmLauncher {

    public static void main(String[] args) throws FileNotFoundException {
        PreparationAtm preparationAtm = new PreparationAtm();
        if (args.length > 0) {
            preparationAtm.argumentsRun(args[0]);
        }
        else {
            preparationAtm.createNewAccount();
        }
    }
}
