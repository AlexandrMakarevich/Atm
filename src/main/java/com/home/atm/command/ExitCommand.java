package com.home.atm.command;

import com.home.atm.storage.Storage;

public class ExitCommand implements Command {

    @Override
    public void execute(Storage storage) {
        System.out.println("Получил команду выйти.");
        System.exit(0);
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
