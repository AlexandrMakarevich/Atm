package com.home.atm.command;

import com.home.atm.storage.Storage;
import java.util.Objects;

public class RepayCreditCommand implements  Command{

    private String currency;
    private int amount;

    public RepayCreditCommand(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void execute(Storage storage) {
        Integer currentValue = storage.getCreditStorage().get(currency);
        if (currentValue == null || currentValue == 0) {
            print();
            return;
        }
        if (currentValue < amount) {
            System.out.println("You are trying to pay more than you need !");
            return;
        }
        Integer balanceAccount = storage.getStorage().get(currency);
        Integer balanceCredit = balanceAccount - amount;
        if (balanceCredit < 0) {
            System.out.println("Not enough money on balance to repay the loan.");
            return;
        }
        Integer currentCreditValue = currentValue - amount;
        storage.getStorage().put (currency, balanceCredit);
        storage.getCreditStorage().put(currency, currentCreditValue);
        String formattedString = String.format("Removed from your credit account %d %s .On you credit balance %d %s ",amount, currency, currentCreditValue, currency );
        System.out.println(formattedString);
    }

    public void print() {
        System.out.println("You don't have credit !");
    }

    @Override
    public String toString() {
        return "RepayCreditCommand{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepayCreditCommand that = (RepayCreditCommand) o;
        return amount == that.amount &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}