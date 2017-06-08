package com.home.atm.database.db_parser;

import com.home.atm.database.db_command.DbCommand;
import com.home.atm.database.db_command.DbExitCommand;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("dbExitParser")
public class DbExitParser implements  DbInputParser {

    private Pattern exitPattern = Pattern.compile("^exit$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher dbExit = exitPattern.matcher(inputString);
        return dbExit.matches();
    }

    @Override
    public DbCommand parseInput(String inputString) {
        Matcher dbexit = exitPattern.matcher(inputString);
        if (dbexit.find()) {
            return new DbExitCommand();
        }
        throw new IllegalArgumentException("Wrong command : " + inputString);
    }
}
