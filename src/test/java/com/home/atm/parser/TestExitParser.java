package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.ExitCommand;
import org.junit.Assert;
import org.junit.Test;

public class TestExitParser extends AbstractInputParserTest{

    private String name = "NotRelevant";

    ExitParser exitParser = new ExitParser(name);

    public void executeAndAssert(String inputString, Command expectedResult) {
        Command actualResult = exitParser.parseInput(inputString);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testAddUSD100() {
        executeAndAssert("exit", new ExitCommand(name));
    }

    @Override
    public InputParser getParser() {
        return exitParser;
    }
}
