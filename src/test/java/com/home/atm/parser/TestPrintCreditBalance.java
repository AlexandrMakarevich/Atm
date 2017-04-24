package com.home.atm.parser;

import com.home.atm.command.Command;
import com.home.atm.command.PrintCreditBalance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPrintCreditBalance extends AbstractInputParserTest{

    private PrintCreditParser printCreditParser;

    @Before
    public void init() {
        printCreditParser = new PrintCreditParser();
    }

    public void executeAndAssert(String inputString, Command expectedResult) {
        Command actualResult = printCreditParser.parseInput(inputString);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testPrintCreditBalance() {
        executeAndAssert("credit balance", new PrintCreditBalance());
    }

    @Override
    public InputParser getParser() {
        return printCreditParser;
    }
}
