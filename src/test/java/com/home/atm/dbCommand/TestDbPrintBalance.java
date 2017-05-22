package com.home.atm.dbCommand;

import com.home.atm.database.DataSource;
import com.home.atm.database.db_command.DbAddCommand;
import com.home.atm.database.db_command.DbPrintBalance;
import com.home.atm.storage.LoaderProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDbPrintBalance {

    private DbPrintBalance dbPrintBalance;
    private TestDbAddCommand testDbAddCommand;
    private DbAddCommand dbAddCommand;
    private String currency = "rub";
    private int amount = 20;
    private int accountId = 1;
    private Connection connection;
    private LoaderProperty loaderProperty;
    private DataSource dataSource;

    @Before
    public void init() throws IOException{
        loaderProperty = new LoaderProperty();
        loaderProperty.loadProperty();
        dataSource = new DataSource();
        connection = dataSource.getConnection();
        dbAddCommand = new DbAddCommand(currency, amount);
        testDbAddCommand = new TestDbAddCommand();
        dbPrintBalance = new DbPrintBalance();
    }

    @Test
    public void testDbPrintBalance() throws SQLException, IOException{
        testDbAddCommand.cleanTable(connection, "debit");
        dbAddCommand.executeDb(accountId);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        dbPrintBalance.executeDb(accountId);
        baos.flush();
        String actualResult = new String(baos.toByteArray());
        Assert.assertEquals("Actual", "Your balance is 20 in currency rub.\n", actualResult);
    }
}
