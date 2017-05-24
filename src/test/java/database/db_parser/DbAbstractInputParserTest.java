package database.db_parser;

import com.home.atm.database.db_parser.DbInputParser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public abstract class DbAbstractInputParserTest {

    @Rule
    public ExpectedException testRuleException = ExpectedException.none();

    @Test
    public void testWrongCommand() {
        String input = "+usd100";
        this.testRuleException.expect(IllegalArgumentException.class);
        this.testRuleException.expectMessage("Wrong command : " + input);
        getParser().parseInput(input);
    }

    public abstract DbInputParser getParser();
}
