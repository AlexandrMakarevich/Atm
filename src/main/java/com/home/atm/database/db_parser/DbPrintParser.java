package com.home.atm.database.db_parser;

import com.home.atm.database.db_command.DbCommand;
import com.home.atm.database.db_command.DbPrintBalance;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbPrintParser implements DbInputParser {

    private Pattern dbPrintPattern = Pattern.compile("^balance$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher dbPrint = dbPrintPattern.matcher(inputString);
        return dbPrint.matches();
    }

    @Override
    public DbCommand parseInput(String inputString) {
        Matcher dbPrint = dbPrintPattern.matcher(inputString);
        if (dbPrint.find()) {
            return new DbPrintBalance();
        }
        throw new IllegalArgumentException("Wrong command : " + inputString);
    }
}
