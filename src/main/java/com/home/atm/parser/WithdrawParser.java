package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.WithdrawCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WithdrawParser implements InputParser{

    private Pattern withdrawPattern = Pattern.compile("^- ([A-Z]{3}) ([0-9]{1,10})$");

    @Override
    public boolean commandMatch(String inputString) {
        Matcher withdraw = withdrawPattern.matcher(inputString);
        return withdraw.matches();
    }

    @Override
    public Command parseInput(String inputString) {
        Matcher withdraw = withdrawPattern.matcher(inputString);
        if(withdraw.find()){
            String currency = withdraw.group(1);
            Integer amount = Integer.parseInt(withdraw.group(2));
            return new WithdrawCommand(currency, amount);
        }
        throw new IllegalArgumentException("Wrong command : " + inputString );
    }
}
