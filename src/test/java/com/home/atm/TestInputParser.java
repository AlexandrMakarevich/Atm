package com.home.atm;

import com.home.atm.command.ExitCommand;
import org.junit.Assert;
import org.junit.Test;
import com.home.atm.command.AddCommand;
import com.home.atm.command.Command;

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

}
