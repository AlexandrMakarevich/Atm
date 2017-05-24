package database.db_parser;

import com.home.atm.database.db_command.DbCommand;
import com.home.atm.database.db_command.DbExitCommand;
import com.home.atm.database.db_parser.DbExitParser;
import com.home.atm.database.db_parser.DbInputParser;
import com.home.atm.storage.LoaderProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class TestDbExitParser extends DbAbstractInputParserTest{

    private DbExitParser dbExitParser;
    private LoaderProperty loaderProperty;

    @Before
    public void init() throws IOException {
        dbExitParser = new DbExitParser();
        loaderProperty = new LoaderProperty();
        loaderProperty.loadProperty();
    }

    @Test
    public void testDbExitCommand() {
        createCommand("exit", new DbExitCommand());
    }

    public void createCommand(String inputCommand, DbCommand expectedResult) {
        DbCommand actualResult = dbExitParser.parseInput(inputCommand);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }
    @Override
    public DbInputParser getParser() {
        return dbExitParser;
    }
}
