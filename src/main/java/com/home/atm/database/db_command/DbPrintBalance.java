package com.home.atm.database.db_command;

import com.home.atm.database.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbPrintBalance implements DbCommand {

    private Connection connection;

    public DbPrintBalance() {
        DataSource dataSource = new DataSource();
         connection = dataSource.getConnection();
    }

    @Override
    public void executeDb(int accountId) throws SQLException {
        String query = "select account_name,currency_name,balance" +
                " from debit d inner join account a on a.id = d.account_id" +
                " inner join currency c on c.id = d.currency_id" +
                " where d.account_id = ?";

        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setInt(1, accountId);
        ResultSet resultSet = prepStatement.executeQuery();
        while (resultSet.next()) {
            System.out.printf("Your balance is %d in currency %s.\n", resultSet.getInt(3), resultSet.getString(2));
        }
        resultSet.close();
        prepStatement.close();
        connection.close();
    }
}
