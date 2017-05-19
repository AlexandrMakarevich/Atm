package com.home.atm.database.db_command;

import com.home.atm.database.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbAddCommand implements DbCommand {

    private Connection connection;
    private String currency;
    private int amount;

    public DbAddCommand(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
        DataSource dataSource = new DataSource();
        connection = dataSource.getConnection();
    }

    @Override
    public void executeDb(int accountId) throws SQLException {
        String query = " insert into debit(account_id, currency_id, balance) "+
        "select ? account_id, c.id, IFNULL(d.balance,?) "+
        "from currency c "+
        "left join debit d "+
        "on d.currency_id = c.id "+
        "where c.currency_name = ? "+
        "ON DUPLICATE KEY UPDATE balance = d.balance + ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setInt(1, accountId);
        prepStatement.setInt(2, amount);
        prepStatement.setString(3, currency);
        prepStatement.setInt(4,amount);
        prepStatement.executeUpdate();
        prepStatement.close();
        connection.close();
    }
}
