package com.home.atm.command;

import com.home.atm.storage.Storage;

public interface Command {
	
	public void execute(Storage storage);

}
