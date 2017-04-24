package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.CreditCommand;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditParser implements InputParser{

    private Pattern creditPattern = Pattern.compile("^credit ([A-Z]{3}) ([0-9]{1,4})$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher credit = creditPattern.matcher(inputString);
        return credit.matches();
    }

    @Override
    public Command parseInput(String inputString) {
        Matcher credit = creditPattern.matcher(inputString);
        if (credit.find()) {
            String currency = credit.group(1);
            Integer amount = Integer.parseInt(credit.group(2));
            return new CreditCommand(currency, amount);
        }
        throw new IllegalArgumentException("Wrong command : " + inputString );
    }
}
