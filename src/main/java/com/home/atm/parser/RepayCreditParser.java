package com.home.atm.parser;

import com.home.atm.command.balance.Command;
import com.home.atm.command.credit.RepayCreditCommand;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepayCreditParser implements InputParser{

    private Pattern repayCreditPattern = Pattern.compile("^repay credit ([A-Z]{3}) ([0-9]{1,4})$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher repayCredit = repayCreditPattern.matcher(inputString);
        return repayCredit.matches();
    }

    @Override
    public Command parseInput(String inputString) {
        Matcher repayCredit = repayCreditPattern.matcher(inputString);
        if (repayCredit.find()) {
            String currency = repayCredit.group(1);
            Integer amount = Integer.parseInt(repayCredit.group(2));
            return new RepayCreditCommand(currency, amount);
        }
        throw new IllegalArgumentException("Wrong command : " + inputString );
    }
}
