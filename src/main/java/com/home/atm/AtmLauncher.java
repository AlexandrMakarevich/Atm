package com.home.atm;

import com.home.atm.command.ServiceAccount;
import java.io.FileNotFoundException;

public class AtmLauncher {

    public static void main(String[] args) throws FileNotFoundException {
        ServiceAccount serviceAccount = new ServiceAccount();
        serviceAccount.launchAccount();
    }
}
