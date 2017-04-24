package com.home.atm.command;

import com.home.atm.storage.Storage;
import java.util.Map;

public class PrintCreditBalance implements Command {

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
