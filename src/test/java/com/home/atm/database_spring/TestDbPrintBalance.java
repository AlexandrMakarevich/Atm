package com.home.atm.database_spring;


import com.home.atm.database.db_command.DbCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:atm-spring.xml")
public class TestDbPrintBalance {

    @Resource(name = "dbPrintBalance")
    private DbCommand dbCommand;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Test
    public void test() throws SQLException {
        cleanTable("currency");
        insertCurrency();
        dbCommand.executeDb(1);
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

    private void insertCurrency() {
        String query = "insert into currency (currency_name) values ('rub'),('usd'),('eur')";
        try {
            Statement statement = dataSource.getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Not have connection", e);
        }
    }
}
