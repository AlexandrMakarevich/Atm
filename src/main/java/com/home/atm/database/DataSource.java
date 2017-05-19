package com.home.atm.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private ComboPooledDataSource cpds;

    public DataSource() {
        cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(System.getProperty("url"));
        cpds.setUser(System.getProperty("userName"));
        cpds.setPassword(System.getProperty("password"));
    }

    public Connection getConnection() {
        try {
            return this.cpds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
