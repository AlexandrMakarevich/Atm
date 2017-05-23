package com.home.atm.dbCommand;

import com.google.common.base.Optional;
import com.home.atm.database.db_command.DbWithdrawCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDbWithdrawCommand extends BaseCommandTest {

    private DbWithdrawCommand dbWithdrawCommand;
    private String currency = "rub";
    private int amount = 20;
    private int accountId = 1;

    @Before
    public void init() throws IOException {
        dbWithdrawCommand = new DbWithdrawCommand(currency, amount);
    }

    @Test
    public void testCheckCurrency() throws SQLException {
        cleanTable( "currency");
        insertCurrency();
        Integer expectedResult = 1;
        Optional<Integer> actualResult = dbWithdrawCommand.checkCurrency(getConnection(), currency);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult.get());
    }

    public void insertCurrency() throws SQLException {
        String query = "insert into currency (currency_name) values ('rub'),('usd'),('eur')";
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(query);
        statement.close();
    }
}
