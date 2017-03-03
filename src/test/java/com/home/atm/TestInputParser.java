package com.home.atm;

import com.home.atm.command.*;
import org.junit.Assert;
import org.junit.Test;

public class TestInputParser {
	
	InputParser inputParser = new InputParser();
	
	@Test
	public void testAddUsd100() {
		executeAndAssert("+ USD 100", new AddCommand("USD", 100));
	}

	public void executeAndAssert(String inputString, Command expectedResult) {
		Command actualResult = inputParser.parseInput(inputString);
		Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
	}

	@Test
	public void testExitCommand() {
		executeAndAssert("exit", new ExitCommand());
	}

	@Test
	public void testPrintAllCurrencies() {
		executeAndAssert("balance", new PrintBalanceCommand(null));
	}

	@Test
	public void testPrintSpecifiedCurrency() {
		executeAndAssert("balance RUB", new PrintBalanceCommand("RUB"));
	}

	@Test
	public void testWithdraw() {
		executeAndAssert("- USD 100", new WithdrawCommand("USD", 100));
	}
}
