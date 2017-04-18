package com.home.atm.command;

import com.home.atm.storage.Storage;
import org.apache.log4j.Logger;
import java.util.Objects;

public class AddCommand implements Command {

	private String currency;
	private int amount;
	private static final Logger LOGGER = Logger.getLogger(AddCommand.class);
	
	public AddCommand(String currency, int amount) {
		this.currency = currency;
		this.amount = amount;
	}

	@Override
	public void execute(Storage storage) {
		Integer currentValue = storage.getStorage().get(currency);
		if (currentValue == null) {
			storage.getStorage().put(currency, amount);
			String formattedString = String.format("Added %d in currency %s", amount, currency);
			print(formattedString);
		}
		else {
			storage.getStorage().put(currency, amount + currentValue);
			Integer currentValue2 = storage.getStorage().get(currency);
			String formattedString = String.format("Added %d %s . On your balance %d %s ",amount, currency, currentValue2 , currency);
			print(formattedString);
		}
	}

	private void print(String input) {
		System.out.println(input);
		LOGGER.info(input);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddCommand other = (AddCommand) obj;
		return Objects.equals(this.currency, other.currency)
				&& Objects.equals(this.amount, other.amount);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddCommand [currency=").append(currency)
				.append(", amount=").append(amount).append("]");
		return builder.toString();
	}
}
