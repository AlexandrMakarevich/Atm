package com.home.atm.database.db_parser;

import com.home.atm.database.db_command.DbAddCommand;
import com.home.atm.database.db_command.DbCommand;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbAddParser implements DbInputParser{

    private Pattern dbAddPattern = Pattern.compile("^\\+ ([a-z]{3}) ([0-9]{1,10})$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher dbAdd = dbAddPattern.matcher(inputString);
        return dbAdd.matches();
    }

    @Override
    public DbCommand parseInput(String inputString) {
        Matcher dbAdd = dbAddPattern.matcher(inputString);
        if(dbAdd.matches()) {
            String currency = dbAdd.group(1);
            Integer amount = Integer.parseInt(dbAdd.group(2));
            return new DbAddCommand(currency, amount);
        }
        throw new IllegalArgumentException("Wrong command : " + inputString );
    }
}
