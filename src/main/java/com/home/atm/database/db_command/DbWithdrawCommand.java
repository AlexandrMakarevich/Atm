package com.home.atm.database.db_command;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import java.sql.*;
import java.util.List;

public class DbWithdrawCommand implements DbCommand {

    private String currency;
    private int amount;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(DbWithdrawCommand.class);

    public DbWithdrawCommand(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String currency, int amount) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void executeDb(int accountName) throws SQLException {
        Optional<Integer> currencyExist = checkCurrency(currency);
        if (!currencyExist.isPresent()) {
            System.out.println("You don't have money on currency " + currency);
            LOGGER.info("You don't have money on currency " + currency);
            return;
        }
        int balance = getBalance(accountName, currencyExist.get());
        if ((balance - amount) < 0) {
            System.out.println("Not enough money on the account!");
            LOGGER.info("Not enough money on the account!");
            return;
        }
        withdrawProcesss(currencyExist.get(), accountName);
        String formattedString = String.format("Removed from your account %d %s.", amount, currency);
        System.out.println(formattedString);
        LOGGER.info(formattedString);
    }

    public void withdrawProcesss(int currencyId, int accountName) throws SQLException {
        String query = "update debit set balance = balance - :p_balance " +
                "where account_id = :p_account_id and currency_id = :p_currency_id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("p_balance", amount);
        namedParameters.addValue("p_account_id", accountName);
        namedParameters.addValue("p_currency_id", currencyId);
        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    public int getBalance(int accountName, int currencyExist) throws SQLException {
        String query = "select balance b from debit " +
                "where account_id = :p_account_id and currency_id = :p_currency_id  for update";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("p_account_id", accountName);
        namedParameters.addValue("p_currency_id", currencyExist);
        List<Integer> balanceList = namedParameterJdbcTemplate.query(query, namedParameters, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("b");
            }
        });

        if (!balanceList.isEmpty()) {
            return balanceList.get(0);
        }
        System.out.println("You don't have money on currency " + currency);
        LOGGER.warn("You don't have money on currency " + currency);
        throw new IllegalStateException("You don't have money on this currency!");
    }

    public Optional<Integer> checkCurrency(String currencyInput) throws SQLException {
        String query = "select id i, currency_name from currency where currency_name = :p_currency_name";
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_currency_name", currencyInput);
        List<Integer> listOfCurrencyId = namedParameterJdbcTemplate.query(query, namedParameters, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("i");
            }
        });
        if (listOfCurrencyId.isEmpty()) {
            return Optional.absent();
        }
        return Optional.of(listOfCurrencyId.get(0));
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
