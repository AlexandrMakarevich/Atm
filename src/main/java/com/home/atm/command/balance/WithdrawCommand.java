package com.home.atm.command.balance;

import com.home.atm.storage.Storage;
import org.apache.log4j.Logger;
import java.util.Objects;

public class WithdrawCommand implements Command {

    private String currency;
    private int amount;
    private static final Logger LOGGER = Logger.getLogger("DEBIT_LOGGER");

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
        String formattedString = String.format("Removed from your account %d %s .On you balance %d %s ",amount, currency, balance, currency );
        System.out.println(formattedString);
        LOGGER.info(formattedString);
    }

    public void printZero() {
        System.out.println("You don't have money on balance!");
        LOGGER.info("You don't have money on balance!");
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
