package com.home.atm.dbParser;

import com.home.atm.database.db_command.DbCommand;
import com.home.atm.database.db_command.DbPrintBalance;
import com.home.atm.database.db_parser.DbInputParser;
import com.home.atm.database.db_parser.DbPrintParser;
import com.home.atm.storage.LoaderProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class TestDbPrintParser extends DbAbstractInputParserTest{

    private DbPrintParser dbPrintParser;
    private LoaderProperty loaderProperty;

    @Before
    public void init() throws IOException {
        dbPrintParser = new DbPrintParser();
        loaderProperty = new LoaderProperty();
        loaderProperty.loadProperty();
    }

    @Test
    public void testDbExitCommand() {
        createCommand("balance", new DbPrintBalance());
    }

    public void createCommand(String inputCommand, DbCommand expectedResult) {
        DbCommand actualResult = dbPrintParser.parseInput(inputCommand);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }
    @Override
    public DbInputParser getParser() {
        return dbPrintParser;
    }
}
