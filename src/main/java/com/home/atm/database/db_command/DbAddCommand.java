package com.home.atm.database.db_command;

import com.google.common.base.Objects;
import com.home.atm.database.DataSource;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbAddCommand implements DbCommand {

    private Connection connection;
    private String currency;
    private int amount;
    private static final Logger LOGGER = Logger.getLogger(DbAddCommand.class);

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
        String formattedString = String.format("Added %d in currency %s.", amount, currency);
        System.out.println(formattedString);
        LOGGER.info(formattedString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbAddCommand that = (DbAddCommand) o;
        return amount == that.amount &&
                Objects.equal(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(currency, amount);
    }
}
