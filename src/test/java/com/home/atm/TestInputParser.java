package com.home.atm;

import org.junit.Assert;
import org.junit.Ignore;
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
	
//	@Ignore
//	@Test
//	public void testAddSmallLetters() {
//		executeAndAssert("+ usd 100", false);
//	}
//	
//	@Ignore
//	@Test
//	public void testAddNegativeAmount() {
//		executeAndAssert("+ USD -100", false);
//	}

}
