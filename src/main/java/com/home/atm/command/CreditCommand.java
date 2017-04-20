package com.home.atm.command;

import com.home.atm.storage.Storage;

public class CreditCommand implements Command{

    private String currency;
    private int amount;

    public CreditCommand(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void execute(Storage storage) {
        Integer currentValue = storage.getStorage().get(currency);
        if (currentValue > 999) {
            System.out.println("Sorry you can't take credit bigger than 999 " + currency);
            return;
        }
        if (amount + currentValue < 999) {
            storage.getStorage().put(currency, amount);
            Integer currentAfterAdd = storage.getStorage().get(currency);
            System.out.printf("You take %d in %s credit.All your credits is %d in %s.", amount, currency, currentAfterAdd, currency);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCommand that = (CreditCommand) o;

        if (amount != that.amount) return false;
        return currency != null ? currency.equals(that.currency) : that.currency == null;
    }

    @Override
    public int hashCode() {
        int result = currency != null ? currency.hashCode() : 0;
        result = 31 * result + amount;
        return result;
    }
}
