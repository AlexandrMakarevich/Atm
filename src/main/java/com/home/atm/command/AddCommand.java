package com.home.atm.command;

import com.home.atm.storage.Storage;

import java.util.Objects;

public class AddCommand implements Command {

	private String currency;
	private int amount;
	
	public AddCommand(String currency, int amount) {
		this.currency = currency;
		this.amount = amount;
	}

	@Override
	public void execute(Storage storage) {
		Integer currentValue = storage.getStorage().get(currency);
		if (currentValue == null) {
			storage.getStorage().put(currency, amount);
			String formattedString = String.format("Добавили %d  в валюте %s ", amount, currency);
			print(formattedString);
		}
		else {
			storage.getStorage().put(currency, amount + currentValue);
			Integer currentValue2 = storage.getStorage().get(currency);
			String formattedString = String.format("Было : %d  Стало %d  в валюте %s ",currentValue, currentValue2 , currency);
			print(formattedString);
		}
	}

	private void print(String input) {
		System.out.println(input);
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
