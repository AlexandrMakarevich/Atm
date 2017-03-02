package com.home.atm.command;

import com.home.atm.storage.Storage;
import java.util.Map;
import java.util.Objects;

public class PrintBalanceCommand implements Command {

    private String currency;

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
        for (Map.Entry<String, Integer> entry : storage.getStorage().entrySet()) {
            print( entry.getKey(),entry.getValue());
        }
    }

    public void print(String inputKey, Integer inputValue) {
        System.out.printf("Ваш баланс составляет %d в валюте %s \n",inputValue,inputKey);
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
