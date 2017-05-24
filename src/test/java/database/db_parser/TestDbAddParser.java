package database.db_parser;

import com.home.atm.database.db_command.DbAddCommand;
import com.home.atm.database.db_command.DbCommand;
import com.home.atm.database.db_parser.DbAddParser;
import com.home.atm.database.db_parser.DbInputParser;
import com.home.atm.storage.LoaderProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class TestDbAddParser extends DbAbstractInputParserTest {

    private DbAddParser dbAddParser;
    private LoaderProperty loaderProperty;

    @Before
    public void init() throws IOException {
        dbAddParser = new DbAddParser();
        loaderProperty = new LoaderProperty();
        loaderProperty.loadProperty();
    }

    @Test
    public void testDbAddRub10() {
        createCommand("+ rub 10", new DbAddCommand("rub", 10));
    }

    public void createCommand(String inputCommand, DbCommand expectedResult) {
        DbCommand actualResult = dbAddParser.parseInput(inputCommand);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Override
    public DbInputParser getParser() {
        return dbAddParser;
    }
}
