package com.home.atm.parser;

import com.home.atm.command.balance.AddCommand;
import com.home.atm.command.balance.Command;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddParser implements InputParser{

    private Pattern addPattern = Pattern.compile("^\\+ ([A-Z]{3}) ([0-9]{1,10})$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher add = addPattern.matcher(inputString);
        return add.matches();
    }

    @Override
    public Command parseInput(String inputString) {
        Matcher add = addPattern.matcher(inputString);
        if(add.find()) {
            String currency = add.group(1);
            Integer amount = Integer.parseInt(add.group(2));
            return new AddCommand(currency, amount);
        }
        throw new IllegalArgumentException("Wrong command : " + inputString );
    }
}
