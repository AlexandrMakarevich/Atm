package database.db_parser;

import com.home.atm.database.db_command.DbCommand;
import com.home.atm.database.db_command.DbWithdrawCommand;
import com.home.atm.database.db_parser.DbInputParser;
import com.home.atm.database.db_parser.DbWithdrawParser;
import com.home.atm.storage.LoaderProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class TestDbWithdrawParser extends DbAbstractInputParserTest{

    private DbWithdrawParser dbWithdrawParser;
    private LoaderProperty loaderProperty;

    @Before
    public void init() throws IOException {
        dbWithdrawParser = new DbWithdrawParser();
        loaderProperty = new LoaderProperty();
        loaderProperty.loadProperty();
    }

    @Test
    public void testDbWithdrawCommand() {
        createCommand("- usd 10", new DbWithdrawCommand("usd", 10));
    }

    public void createCommand(String inputCommand, DbCommand expectedResult) {
        DbCommand actualResult = dbWithdrawParser.parseInput(inputCommand);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Override
    public DbInputParser getParser() {
        return dbWithdrawParser;
    }
}
