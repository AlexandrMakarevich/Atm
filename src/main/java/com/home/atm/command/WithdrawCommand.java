package com.home.atm.command;

import com.home.atm.storage.Storage;
import java.util.Objects;

public class WithdrawCommand implements Command {

    private String currency;
    private int amount;

    public WithdrawCommand(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void execute(Storage storage) {
        Integer currentValue = storage.getStorage().get(currency);
        if (currentValue == null) {
            printZero();
            return;
        }
        Integer balance = currentValue - amount;
        if (balance < 0) {
            printZero();
           return;
        }
        storage.getStorage().put (currency, balance);
        String formattedString = String.format("На вашем счету %d в валюте %s",balance, currency );
        System.out.println(formattedString);
    }

    public void printZero() {
        System.out.println("Нет средств на счету!");
    }

    @Override
    public String toString() {
        return "WithdrawCommand{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithdrawCommand that = (WithdrawCommand) o;
        return amount == that.amount &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}
