package com.home.atm.command.balance;

import com.home.atm.PreparationAtm;
import com.home.atm.storage.Storage;
import java.util.Scanner;

public class ServiceAccount {

    private PreparationAtm preparationAtm = new PreparationAtm();

    public void launchAccount(Storage storage) {
        System.out.println("Enter the account number :");
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String account = in.nextLine();
            storage.setCurrentAccount(account);
            preparationAtm.process(storage);
        }
    }
}
