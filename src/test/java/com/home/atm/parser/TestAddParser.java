package com.home.atm.parser;

import com.home.atm.command.balance.AddCommand;
import com.home.atm.command.balance.Command;
import org.junit.Assert;
import org.junit.Test;

public class TestAddParser extends AbstractInputParserTest{

    private AddParser addParser =  new AddParser();

    public void executeAndAssert(String inputString, Command expectedResult) {
        Command actualResult = addParser.parseInput(inputString);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testAddUsd100() {
        executeAndAssert("+ USD 100", new AddCommand("USD",100));
    }

    @Override
    public InputParser getParser() {
        return addParser;
    }
}
