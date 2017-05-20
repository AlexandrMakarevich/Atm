package com.home.atm.dbCommand;

import com.google.common.base.Optional;
import com.home.atm.database.DataSource;
import com.home.atm.database.db_command.DbAddCommand;
import com.home.atm.storage.LoaderProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.sql.*;

public class TestDbAddCommand {

    private LoaderProperty loaderProperty;
    private DbAddCommand dbAddCommand;
    private DataSource dataSource;
    private Connection connection;
    private String currency = "rub";
    private int amount = 20;
    private int accountId = 1;

    @Before
    public void init() throws IOException {
        loaderProperty = new LoaderProperty();
        loaderProperty.loadProperty();
        dbAddCommand = new DbAddCommand(currency, amount);
        dataSource = new DataSource();
        connection = dataSource.getConnection();
    }

    @Test
    public void testAddCommand() throws SQLException {
        cleanTable("debit");
        dbAddCommand.executeDb(accountId);
        Optional<Integer> data = getDataTable();
        int expectedResult = data.get();
        Assert.assertEquals("Actual result must be expected", expectedResult, amount);
    }

    public void cleanTable(String... tables) throws SQLException{
        Statement statement = connection.createStatement();
        for (String table : tables) {
            statement.executeUpdate("truncate table " + table);
        }
        statement.close();
    }

    public Optional<Integer> getDataTable() throws SQLException {
        String query = "select balance" +
                " from debit d inner join account a on a.id = d.account_id" +
                " inner join currency c on c.id = d.currency_id" +
                " where d.account_id = ? and d.currency_id = c.currency_name = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setInt(1, accountId);
        prepStatement.setString(2, currency);
        ResultSet resultSet = prepStatement.executeQuery();
        while (resultSet.next()) {
            return  Optional.of(resultSet.getInt(1));
        }
        return Optional.absent();
    }
}
