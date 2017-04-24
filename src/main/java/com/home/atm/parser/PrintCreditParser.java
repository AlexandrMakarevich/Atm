package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.PrintCreditBalance;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintCreditParser implements InputParser {

    private Pattern printCreditPattern = Pattern.compile("^credit balance$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher printCredit = printCreditPattern.matcher(inputString);
        return printCredit.matches();
    }

    @Override
    public Command parseInput(String inputString) {
        Matcher printCredit = printCreditPattern.matcher(inputString);
        if (printCredit.find()) {
            return new PrintCreditBalance();
        }
        throw new IllegalArgumentException("Wrong command : " + inputString );
    }
}
