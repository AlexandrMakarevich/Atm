package com.home.atm.database_spring;

import com.home.atm.database.db_command.DbAddCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:atm-spring.xml")
public class TestDbAddCommand extends BaseCommandTest {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private DbAddCommand dbAddCommand;
    private String currencyName = "usd";
    private String accountName = "ivanov";
    private int balance = 543;

    @Rule
    public ExpectedException testRuleException = ExpectedException.none();

    @Before
    public void init() {
        cleanTable("account", "currency", "debit");
        dbAddCommand = new DbAddCommand(namedParameterJdbcTemplate, currencyName, balance);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCheckAddMoneyOnBalance() throws SQLException {
        int accountId = insert("account", "account_name", accountName);
        insert("currency", "currency_name", currencyName);
        dbAddCommand.executeDb(accountId);
        int actualBalance = getBalanceOnCurrency(accountId, currencyName);
        Assert.assertEquals("ActualBalance must be the same as balance", balance, actualBalance);
    }

    @Test
    public void testCaseWhenInsertNotHappen() throws SQLException {
        int accountId = insert("account", "account_name", accountName);
        this.testRuleException.expect(IllegalStateException.class);
        this.testRuleException.expectMessage("No column has been changed !");
        dbAddCommand.executeDb(accountId);
    }

    public int getBalanceOnCurrency(int accountId, String currencyName) {
        String query = "select balance b from debit d" +
                " inner join currency c on c.id = d.currency_id" +
                " where account_id = :p_account_id and c.currency_name = :p_currency_name";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("p_account_id", accountId);
        namedParameters.addValue("p_currency_name", currencyName);
        return namedParameterJdbcTemplate.queryForObject(query, namedParameters, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("b");
            }
        });
    }
}
