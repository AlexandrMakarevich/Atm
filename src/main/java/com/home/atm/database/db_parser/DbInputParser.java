package com.home.atm.database.db_parser;

import com.home.atm.database.db_command.DbCommand;

public interface DbInputParser {

    boolean commandMatch(String inputString);

    DbCommand parseInput(String inputString);
}
