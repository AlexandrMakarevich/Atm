package com.home.atm.database_spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:atm-spring.xml")
public class TestCurrencyDaoImpl {

    @Resource(name="currencyDaoImpl")
    private CurrencyDao currencyDao;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Test
    public void testInsertAndCheckCurrency() throws SQLDataException {
        String actualCurrencyName = "rub";
        cleanTable("currency");
        currencyDao.insertCurrency(actualCurrencyName);
        Currency currency = currencyDao.findCurrencyByName(actualCurrencyName);
        String expectedCurrencyName = currency.getCurrencyName();
        Assert.assertEquals("ActualCurrencyName must be the same that expectedCurrencyName",
                expectedCurrencyName,actualCurrencyName);
    }

    public void cleanTable(String... tables) {
        try {
            Statement statement = dataSource.getConnection().createStatement();
            for (String table : tables) {
                statement.executeUpdate("truncate table " + table);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error during table clean up", e);
        }
    }
}
