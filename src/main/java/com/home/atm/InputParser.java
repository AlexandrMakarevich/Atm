package com.home.atm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.home.atm.command.AddCommand;
import com.home.atm.command.Command;
import com.home.atm.command.ExitCommand;
import com.home.atm.command.PrintBalanceCommand;

public class InputParser {

    private Pattern addPattern = Pattern.compile("^[+]{1} ([A-Z]{3}) ([0-9]{1,10})$");

    private Pattern exitPattern = Pattern.compile("^exit$");

	private Pattern printPattern = Pattern.compile("^balance$|^balance ([A-Z]{3})$");
	
	public Command parseInput(String inputString) {
        Matcher m = addPattern.matcher(inputString);
		if(m.find()) {
			String currency = m.group(1);
			Integer amount = Integer.parseInt(m.group(2));
			return new AddCommand(currency, amount);
		}
		Matcher f = exitPattern.matcher(inputString);
        if(f.find()) {
            return new ExitCommand();
        }
        Matcher p = printPattern.matcher(inputString);
		if(p.find()) {
			String currency = p.group(1);
			return new PrintBalanceCommand(currency);
		}
		throw new IllegalArgumentException("Не допустимая команда : " + inputString );
	}
}
