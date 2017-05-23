package com.home.atm.database.db_parser;

import com.home.atm.database.db_command.DbCommand;
import java.util.ArrayList;
import java.util.List;

public class DbDelegatedInputParser {

    private List<DbInputParser> inputParsers;

    public DbDelegatedInputParser() {
        inputParsers = new ArrayList<>();
        inputParsers.add(new DbPrintParser());
        inputParsers.add(new DbExitParser());
        inputParsers.add(new DbAddParser());
        inputParsers.add(new DbWithdrawParser());
    }

    public DbCommand defaultParseInput(String inputString) {
        for (DbInputParser inputParser : inputParsers ) {
            if (inputParser.commandMatch(inputString)) {
                return inputParser.parseInput(inputString);
            }
        }
        throw new IllegalArgumentException("Wrong command " + inputString);
    }
}
