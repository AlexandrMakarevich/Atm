package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.ExitCommand;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExitParser implements InputParser {

    private Pattern exitPattern = Pattern.compile("^exit$");

    @Override
    public Command parseInput(String inputString) {
        Matcher exit = exitPattern.matcher(inputString);
        if( exit.matches()){
            return new ExitCommand();
        }
        throw new IllegalArgumentException("Wrong command : " + inputString );
    }

    @Override
    public boolean commandMatch(String inputString) {
        Matcher exit = exitPattern.matcher(inputString);
        return exit.matches();
    }
}
