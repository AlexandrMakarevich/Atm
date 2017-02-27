package com.home.atm.command;


import java.util.Objects;

public class AddCommand implements Command {
	private String currency;
	private int amount;
	
	public AddCommand(String currency, int amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public void execute(Storage storage) {
		storage.addAmount(currency, amount);
		String formattedString = String.format(" Добавили %d  в валюте %s ",amount, currency );
		System.out.println(formattedString);
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
