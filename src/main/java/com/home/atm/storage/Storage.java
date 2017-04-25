package com.home.atm.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Storage {

	@JsonProperty
	private Map<String, Map<String, Integer>> accountStorage;

	@JsonProperty
	private Map<String, Map<String, Integer>> creditStorage;

	@JsonIgnore
	private String currentAccount;

	public Storage(Map<String, Map<String, Integer>> accountStorage, Map<String, Map<String, Integer>> creditStorage) {
		this.accountStorage = accountStorage;
		this.creditStorage = creditStorage;
	}

	public Storage(Map<String, Map<String, Integer>> accountStorage) {
		this.accountStorage = accountStorage;
	}

	public Storage() {

	}
	public Map<String, Integer> getCreditStorage() {
		Map<String, Integer> storage = creditStorage.get(currentAccount);
		if (storage == null) {
			storage = new HashMap<>();
			creditStorage.put(currentAccount, storage);
		}
		return storage;
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

	public Map<String, Map<String, Integer>> getCredit() {
		return creditStorage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Storage storage = (Storage) o;
		return Objects.equals(accountStorage, storage.accountStorage) &&
				Objects.equals(creditStorage, storage.creditStorage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountStorage, creditStorage);
	}
}