package com.home.atm.parser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public abstract class AbstractInputParserTest {

    @Rule
    public ExpectedException testRuleException = ExpectedException.none();

    @Test
    public void testWrongCommand() {
        String input = "+ usd 100";
        this.testRuleException.expect(IllegalArgumentException.class);
        this.testRuleException.expectMessage("Wrong command : " + input);
        getParser().parseInput(input);
    }

    public abstract  InputParser getParser();
}
