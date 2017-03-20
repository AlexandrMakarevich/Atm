package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.ExitCommand;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExitParser implements InputParser {

    private Pattern exitPattern = Pattern.compile("^exit$");
    private String fileName;

    public ExitParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Command parseInput(String inputString) {
        Matcher exit = exitPattern.matcher(inputString);
        if( exit.matches()){
            return new ExitCommand(fileName);
        }
        throw new IllegalArgumentException("Не допустимая команда : " + inputString );
    }

    @Override
    public boolean commandMatch(String inputString) {
        Matcher exit = exitPattern.matcher(inputString);
        return exit.matches();
    }
}
