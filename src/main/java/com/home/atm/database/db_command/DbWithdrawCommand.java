package com.home.atm.database.db_command;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.home.atm.database.DataSource;
import org.apache.log4j.Logger;
import java.sql.*;

public class DbWithdrawCommand implements DbCommand {

    private String currency;
    private int amount;
    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(DbWithdrawCommand.class);

    public DbWithdrawCommand(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
        DataSource dataSource = new DataSource();
        connection = dataSource.getConnection();
    }

    @Override
    public void executeDb(int accountName) throws SQLException {
        Optional<Integer> currencyExist = checkCurrency(connection, currency);
        if (!currencyExist.isPresent()) {
            System.out.println("You don't have money on currency " + currency);
            LOGGER.info("You don't have money on currency " + currency);
            return;
        }
        connection.setAutoCommit(false);
        int balance = getBalance(accountName, currencyExist.get());
        if ((balance - amount) < 0) {
            System.out.println("Not enough money on the account!");
            LOGGER.info("Not enough money on the account!");
            connection.rollback();
            connection.close();
            return;
        }
        withdrawProcesss(currencyExist.get(), accountName);
        connection.commit();
        connection.close();
        String formattedString = String.format("Removed from your account %d %s.", amount, currency);
        System.out.println(formattedString);
        LOGGER.info(formattedString);
    }

    public void withdrawProcesss(int currencyId, int accountName) throws SQLException {
        String query = "update debit set balance = balance - ? " +
                "where account_id = ? and currency_id = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setInt(1, amount);
        prepStatement.setInt(2, accountName);
        prepStatement.setInt(3, currencyId);
        prepStatement.executeUpdate();
        prepStatement.close();
    }

    public int getBalance(int accountName, int currencyExist) throws SQLException {
        String query = "select balance from debit " +
                "where account_id = ? and currency_id = ? for update";
        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setInt(1, accountName);
        prepStatement.setInt(2, currencyExist);
        ResultSet resultSet = prepStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        System.out.println("You don't have money on currency " + currency);
        LOGGER.warn("You don't have money on currency " + currency);
        connection.close();
        throw new IllegalStateException("You don't have money on this currency!");
    }

    public Optional<Integer> checkCurrency(Connection connection, String currencyInput) throws SQLException {
        String query = "select id, currency_name from currency where currency_name = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setString(1, currencyInput);
        ResultSet resultSet = prepStatement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(resultSet.getInt(1));
        }
        return Optional.absent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbWithdrawCommand that = (DbWithdrawCommand) o;
        return amount == that.amount &&
                Objects.equal(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(currency, amount);
    }
}
