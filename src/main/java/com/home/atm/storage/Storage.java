package com.home.atm.storage;

import java.util.HashMap;
import java.util.Map;

public class Storage {

	private Map<String, Integer> storage = new HashMap<>();

	public Storage(Map<String, Integer> map) {
			storage = map;
	}

	public Storage() {

	}

	public Map<String, Integer> getStorage() {
		return storage;
	}
}

