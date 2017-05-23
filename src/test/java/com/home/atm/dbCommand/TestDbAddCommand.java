package com.home.atm.dbCommand;

import com.home.atm.database.db_command.DbAddCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.sql.*;

public class TestDbAddCommand extends BaseCommandTest {

    private DbAddCommand dbAddCommand;
    private String currency = "rub";
    private int amount = 20;
    private int accountId = 1;

    @Before
    public void init() throws IOException {
        dbAddCommand = new DbAddCommand(currency, amount);
    }

    @Test
    public void testAddCommand() throws SQLException {
        cleanTable("debit");
        dbAddCommand.executeDb(accountId);
        int expectedResult = getDataTable();
        Assert.assertEquals("Actual result must be expected", expectedResult, amount);
    }

    private int getDataTable() throws SQLException {
        String query = "select balance" +
                " from debit d inner join account a on a.id = d.account_id" +
                " inner join currency c on c.id = d.currency_id" +
                " where d.account_id = ? and d.currency_id = c.currency_name = ?";
        PreparedStatement prepStatement = getConnection().prepareStatement(query);
        prepStatement.setInt(1, accountId);
        prepStatement.setString(2, currency);
        ResultSet resultSet = prepStatement.executeQuery();
        if (!resultSet.next()) {
             Assert.fail("Balance on this currency not found");
        }
        return resultSet.getInt(1);
    }
}
