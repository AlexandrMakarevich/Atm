package com.home.atm.command;

import com.home.atm.storage.Storage;
import org.apache.log4j.Logger;
import java.util.Objects;

public class CreditCommand implements Command {

    private String currency;
    private int amount;
    private static final Logger LOGGER = Logger.getLogger(CreditCommand.class);

    public CreditCommand(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void execute(Storage storage) {
        Integer currentValue = storage.getCreditStorage().get(currency);
        if (amount > 1000) {
            String formattedString = String.format("Sorry you can take credit not bigger than 1000 in any currency.\n");
            System.out.println(formattedString);
            LOGGER.info(formattedString);
            return;
        }
        if (currentValue == null) {
            storage.getStorage().put(currency, amount);
            storage.getCreditStorage().put(currency, amount);
            String formattedString = String.format("You take credit %d in currency %s.\n", amount, currency);
            System.out.printf(formattedString);
            LOGGER.info(formattedString);
            return;
        }
        if (currentValue + amount > 1000) {
            String formattedString = String.format("Sorry sum of all your credits bigger than 1000 in currency %s.\n", currency);
            System.out.println(formattedString);
            LOGGER.info(formattedString);
            return;
        }
            Integer storageAccountState = storage.getStorage().get(currency);
            storage.getStorage().put(currency, amount + storageAccountState);
            storage.getCreditStorage().put(currency, amount + currentValue);
            Integer currentAfterAdd = storage.getCreditStorage().get(currency);
            String formattedString = String.format("You take %d in %s credit.All your credits is %d in %s.\n", amount, currency, currentAfterAdd, currency);
            System.out.println(formattedString);
            LOGGER.info(formattedString);
            return;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCommand that = (CreditCommand) o;
        return amount == that.amount &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}
