package com.home.atm.parser;

import com.home.atm.command.balance.Command;
import com.home.atm.command.credit.CreditCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCreditParser extends AbstractInputParserTest{

    private CreditParser creditParser;

    @Before
    public void init() {
        creditParser = new CreditParser();
    }

    public void executeAndAssert(String inputString, Command expectedResult) {
        Command actualResult = creditParser.parseInput(inputString);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testTakeCredit333() {
        executeAndAssert("credit RUB 333", new CreditCommand("RUB",333));
    }

    @Override
    public InputParser getParser() {
        return creditParser;
    }
}
