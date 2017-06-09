package com.home.atm.database_spring;

import com.home.atm.database.db_command.DbCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Rollback;
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
public class TestDbPrintBalance extends BaseCommandTest {

    @Resource(name = "dbPrintBalance")
    private DbCommand dbCommand;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Before
    public void init() {
        cleanTable("debit", "currency", "account");
    }

    @Test
    @Rollback(false)
    @Transactional
    public void test() throws SQLException, IOException {
        String currencyName = "rub";
        String accountName = "petrov";
        int balance = 130;
        int currencyId = insert("currency", "currency_name", currencyName);
        int accountId = insert("account", "account_name", accountName);
        insertBalance(accountId, currencyId, balance);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        dbCommand.executeDb(accountId);
        baos.flush();
        String expectedResult = String.format("Your balance is %d in currency %s.\n", balance, currencyName);
        String actualResult = new String(baos.toByteArray());
        Assert.assertEquals("Actual result must be expected ", expectedResult, actualResult);
    }
}
