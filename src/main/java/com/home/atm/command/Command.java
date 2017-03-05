package com.home.atm.command;

import com.home.atm.storage.Storage;

public interface Command {
	
	 void execute(Storage storage);

}
