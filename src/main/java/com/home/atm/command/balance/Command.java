package com.home.atm.command.balance;

import com.home.atm.storage.Storage;

public interface Command {
	
	 void execute(Storage storage);

}
