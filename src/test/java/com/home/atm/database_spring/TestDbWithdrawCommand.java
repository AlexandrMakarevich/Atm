package com.home.atm.database_spring;

import com.google.common.base.Optional;
import com.home.atm.database.db_command.DbWithdrawCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:atm-spring.xml")
public class TestDbWithdrawCommand extends BaseCommandTest {

    private DbWithdrawCommand dbWithdrawCommand;
    private String currencyName = "rub";
    private String accountName = "petrov";
    int withdrawAmount = 345;
    int balance = 600;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Before
    public void init() {
        dbWithdrawCommand = new DbWithdrawCommand(namedParameterJdbcTemplate, currencyName, withdrawAmount);
        cleanTable("account", "currency", "debit");
    }

    @Rule
    public ExpectedException testRuleException = ExpectedException.none();

    @Test
    @Transactional
    public void testExecuteDb() throws SQLException {
        int accountId = insert("account", "account_name", accountName);
        int currencyId = insert("currency", "currency_name", currencyName);
        insertBalance(accountId, currencyId, balance);
        dbWithdrawCommand.executeDb(accountId);
        int expectedResult = balance - withdrawAmount;
        int actualResult = dbWithdrawCommand.getBalance(accountId, currencyId);
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testNotEnoughMoney() throws SQLException, IOException {
        int balanceLitleThanCanWithdraw = 100;
        int accountId = insert("account", "account_name", accountName);
        int currencyId = insert("currency", "currency_name", currencyName);
        insertBalance(accountId, currencyId, balanceLitleThanCanWithdraw);
        dbWithdrawCommand.executeDb(accountId);
        String expectedResult = "Not enough money on the account!\n";
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        dbWithdrawCommand.executeDb(accountId);
        bo.flush();
        String actualResult = new String(bo.toByteArray());
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testWhereCurrencyNotExist() throws SQLException, IOException {
        int accountId = insert("account", "account_name", accountName);
        String expectedResult = "You don't have money on currency " + currencyName + "\n";
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        dbWithdrawCommand.executeDb(accountId);
        bo.flush();
        String actualResult = new String(bo.toByteArray());
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testGetBalanceWhereBalanceZero() throws SQLException {
        int accountId = insert("account", "account_name", accountName);
        insert("currency", "currency_name", currencyName);
        this.testRuleException.expect(IllegalStateException.class);
        this.testRuleException.expectMessage("You don't have money on this currency!");
        dbWithdrawCommand.executeDb(accountId);
    }

    @Test
    public void testGetBalance() throws SQLException {
        int accountId = insert("account", "account_name", accountName);
        int currencyId = insert("currency", "currency_name", currencyName);
        insertBalance(accountId, currencyId, balance);
        int expectedResult = balance;
        Optional<Integer> idCurrency = dbWithdrawCommand.checkCurrency(currencyName);
        int actualResult = dbWithdrawCommand.getBalance(accountId, idCurrency.get());
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }
}
