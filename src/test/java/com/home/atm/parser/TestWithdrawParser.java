package com.home.atm.parser;

import com.home.atm.command.balance.Command;
import com.home.atm.command.balance.WithdrawCommand;
import org.junit.Assert;
import org.junit.Test;

public class TestWithdrawParser extends AbstractInputParserTest {

    WithdrawParser withdrawParser = new WithdrawParser();

    public void executeAndAssert(String inputString, Command expectedResult) {
        Command actualResult = withdrawParser.parseInput(inputString);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testWithdrawUsd100() {
        executeAndAssert("- USD 100", new WithdrawCommand("USD", 100));
    }

    @Override
    public InputParser getParser() {
        return withdrawParser;
    }
}
