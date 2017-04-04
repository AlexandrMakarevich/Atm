package com.home.atm.storage;

import java.util.HashMap;
import java.util.Map;

public class Storage {

	private Map<String, Map<String, Integer>> accountStorage;

	private String currentAccount;

	public Storage(Map<String, Map<String, Integer>> accountStorage) {
		this.accountStorage = accountStorage;
	}

	public Storage() {

	}

	public Map<String, Integer> getStorage() {
		Map<String, Integer> storage = accountStorage.get(currentAccount);
		if (storage == null) {
			storage = new HashMap<>();
			accountStorage.put(currentAccount, storage);
		}
		return storage;
	}

	public void setCurrentAccount(String currentAccount) {
		this.currentAccount = currentAccount;
	}

	public Map<String, Map<String, Integer>> getAccountStorage() {
		return accountStorage;
	}
}