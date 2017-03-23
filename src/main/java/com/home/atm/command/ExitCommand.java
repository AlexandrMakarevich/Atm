package com.home.atm.command;

import com.home.atm.storage.Storage;
import com.home.atm.storage.StorageSaver;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;

public class ExitCommand implements Command {

    private StorageSaver storageSever ;
    private static final Logger LOGGER = Logger.getLogger(ExitCommand.class);

    public ExitCommand() {
       storageSever = new StorageSaver();
    }

    @Override
    public void execute(Storage storage) {
       try{
           storageSever.writeData(storage);
       }
       catch(FileNotFoundException ex) {
           System.out.println("������ ���������� ����� " + ex.getMessage());
           LOGGER.warn("������ ���������� ����� ", ex);
       }
        System.out.println("������� ������� �����.");
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
