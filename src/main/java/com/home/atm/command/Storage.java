package com.home.atm.command;

import java.util.HashMap;
import java.util.Map;

public class Storage {
	
	private Map<String, Integer> storage = new HashMap<>();
	

	public void addAmount(String currency, int amount) {
		storage.put(currency, amount);
	
	}

}

