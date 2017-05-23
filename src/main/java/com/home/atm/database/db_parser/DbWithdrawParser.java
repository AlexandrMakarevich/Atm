package com.home.atm.database.db_parser;

import com.home.atm.database.db_command.DbCommand;
import com.home.atm.database.db_command.DbWithdrawCommand;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbWithdrawParser implements DbInputParser {

    private Pattern dbWithdrawPattern = Pattern.compile("^- ([a-z]{3}) ([0-9]{1,10})$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher dbWithdraw = dbWithdrawPattern.matcher(inputString);
        return dbWithdraw.matches();
    }

    @Override
    public DbCommand parseInput(String inputString) {
        Matcher dbWithdraw = dbWithdrawPattern.matcher(inputString);
        if (dbWithdraw.matches()) {
            String currency = dbWithdraw.group(1);
            Integer amount = Integer.valueOf(dbWithdraw.group(2));
            return new DbWithdrawCommand(currency, amount);
        }
        throw new IllegalArgumentException("Wrong command : " + inputString);
    }
}
