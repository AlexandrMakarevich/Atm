package com.home.atm.parser;

import com.home.atm.command.balance.Command;
import com.home.atm.command.credit.RepayCreditCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRepayCreditParser extends AbstractInputParserTest {

    private RepayCreditParser repayCreditParser;

    @Before
    public void init() {
        repayCreditParser = new RepayCreditParser();
    }

    public void executeAndAssert(String inputString, Command expectedResult) {
        Command actualResult = repayCreditParser.parseInput(inputString);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testRepayCredit333() {
        executeAndAssert("repay credit RUB 333", new RepayCreditCommand("RUB", 333));
    }

    @Override
    public InputParser getParser() {
        return repayCreditParser;
    }
}
