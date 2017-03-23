package com.home.atm.command;

import com.home.atm.PreparationAtm;
import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageLoader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static com.home.atm.storage.StorageSaver.filename;

public class ServiceAccount {

    private StorageLoader storageLoader = new StorageLoader();
    private PreparationAtm preparationAtm = new PreparationAtm();

    public void launchAccount()  {
        System.out.println("Enter the account number :");
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            try{
                String account = in.nextLine();
                filename = account;
                Storage storage = storageLoader.loadStorage(account);
                preparationAtm.process(storage);
            }
            catch(FileNotFoundException ex) {
                System.out.println("Account does not exist.\nEnter correct account number :");
            }
        }
    }
}
