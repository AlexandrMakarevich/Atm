package com.home.atm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.home.atm.command.*;

public class InputParser {

    private Pattern addPattern = Pattern.compile("^\\+ ([A-Z]{3}) ([0-9]{1,10})$");

    private Pattern exitPattern = Pattern.compile("^exit$");

	private Pattern printPattern = Pattern.compile("^balance$|^balance ([A-Z]{3})$");

    private Pattern withdrawPattern = Pattern.compile("^- ([A-Z]{3}) ([0-9]{1,10})$");
	
	public Command parseInput(String inputString) {
        Matcher add = addPattern.matcher(inputString);
		if (add.find()) {
			String currency = add.group(1);
			Integer amount = Integer.parseInt(add.group(2));
			return new AddCommand(currency, amount);
		}
		Matcher withdraw = withdrawPattern.matcher(inputString);
        if (withdraw.find()) {
            String currency = withdraw.group(1);
            Integer amount = Integer.parseInt(withdraw.group(2));
            return new WithdrawCommand(currency, amount);
        }
		Matcher exit = exitPattern.matcher(inputString);
        if (exit.find()) {
            return new ExitCommand();
        }
        Matcher print = printPattern.matcher(inputString);
		if (print.find()) {
			String currency = print.group(1);
			return new PrintBalanceCommand(currency);
		}
		throw new IllegalArgumentException("Не допустимая команда : " + inputString );
	}
}
