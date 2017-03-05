package com.home.atm.parser;

import com.home.atm.command.Command;

public interface InputParser {

    boolean commandMatch(String inputString);

    Command parseInput(String inputString);

}
