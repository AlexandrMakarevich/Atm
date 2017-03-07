package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.PrintBalanceCommand;
import org.junit.Assert;
import org.junit.Test;

public class TestPrintParser extends AbstractInputParserTest {

    PrintParser printParser = new PrintParser();

    public void executeAndAssert(String inputString, Command expectedResult) {
        Command actualResult = printParser.parseInput(inputString);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testPrintBalanceWithoutCurrency() {
        executeAndAssert("balance", new PrintBalanceCommand(null));
    }

    @Test
    public void testPrintBalanceWithSomeCurrency() {
        executeAndAssert("balance USD", new PrintBalanceCommand("USD"));
    }

    @Override
    public InputParser getParser() {
        return printParser;
    }
}
