package com.home.atm.command.credit;

import com.home.atm.command.balance.Command;
import com.home.atm.storage.Storage;
import org.apache.log4j.Logger;

import java.util.Map;

public class PrintCreditBalance implements Command {

    private static final Logger LOGGER = Logger.getLogger(PrintCreditBalance.class);

    @Override
    public void execute(Storage storage) {
        boolean creditStorageState = storage.getCreditStorage().isEmpty();
        if (creditStorageState == true) {
            System.out.println("You lucky!You don't have credits!");
            return;
        }
        for (Map.Entry<String, Integer> entry : storage.getCreditStorage().entrySet()) {
            print(entry.getKey(), entry.getValue());
        }
    }

    public void print(String inputKey, Integer inputValue) {
        System.out.printf("Your credit balance is %d in currency %s.\n", inputValue, inputKey);
        LOGGER.info("Your credit balance is " + inputValue + " in currency " + inputKey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
