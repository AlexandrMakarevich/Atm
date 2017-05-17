package com.home.atm.parser;

import com.home.atm.command.balance.Command;
import com.home.atm.command.balance.PrintBalanceCommand;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintParser implements InputParser {

    private Pattern printPattern = Pattern.compile("^balance$|^balance ([A-Z]{3})$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher print = printPattern.matcher(inputString);
        return print.matches();
    }

    @Override
    public Command parseInput(String inputString) {
        Matcher print = printPattern.matcher(inputString);
        if(print.find()) {
            String currency = print.group(1);
            return new PrintBalanceCommand(currency);
        }
        throw new IllegalArgumentException("Wrong command : " + inputString );
    }
}
