package com.home.atm.parser;

import com.home.atm.command.Command;
import java.util.ArrayList;
import java.util.List;

public class DelegatedInputParser {

    private List<InputParser> inputParsers;

    public DelegatedInputParser() {
       inputParsers = new ArrayList<>();
        inputParsers.add(new ExitParser());
        inputParsers.add(new PrintParser());
        inputParsers.add(new AddParser());
        inputParsers.add(new WithdrawParser());
    }

    public Command defaultParseInput(String inputString) {
       for (InputParser inputParser : inputParsers ) {
          if (inputParser.commandMatch(inputString)) {
             return inputParser.parseInput(inputString);
          }
       }
       throw new IllegalArgumentException("Не выполнимая команда " + inputString);
    }
}
