package com.home.atm.command.balance;

import com.home.atm.storage.Storage;
import org.apache.log4j.Logger;
import java.util.Map;
import java.util.Objects;

public class PrintBalanceCommand implements Command {

    private String currency;
    private static final Logger LOGGER = Logger.getLogger(PrintBalanceCommand.class);

    public PrintBalanceCommand(String currency) {
        this.currency = currency;
    }

    @Override
    public void execute(Storage storage) {
        if (currency != null) {
            printSpecifiedCurrency(currency, storage);
            return;
        }
        printAllCurrencies(storage);
    }

    public void printSpecifiedCurrency(String currency, Storage storage) {
        print(currency,storage.getStorage().get(currency));
    }

    public void printAllCurrencies(Storage storage) {
        if (storage.getStorage().isEmpty()) {
            System.out.println("Account is empty.");
            return;
        }
        for (Map.Entry<String, Integer> entry : storage.getStorage().entrySet()) {
            print( entry.getKey(),entry.getValue());
        }
    }

    public void print(String inputKey, Integer inputValue) {
        System.out.printf("Your balance is %d in currency %s.\n",inputValue,inputKey);
        LOGGER.info("Your balance is " + inputValue + " in currency " + inputKey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintBalanceCommand that = (PrintBalanceCommand) o;
        return Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency);
    }

    @Override
    public String toString() {
        return "PrintBalanceCommand{" +
                "currency='" + currency + '\'' +
                '}';
    }
}
