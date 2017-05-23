package com.home.atm.dbCommand;

import com.home.atm.database.DataSource;
import com.home.atm.storage.LoaderProperty;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseCommandTest {

    private Connection connection;

    public BaseCommandTest() {
        LoaderProperty loaderProperty = new LoaderProperty();
        try {
            loaderProperty.loadProperty();
        } catch (IOException e) {
            throw new RuntimeException("Error during loading properties", e);
        }
        connection = new DataSource().getConnection();
    }

    public void cleanTable(String... tables) throws SQLException {
        Statement statement = connection.createStatement();
        for (String table : tables) {
            statement.executeUpdate("truncate table " + table);
        }
        statement.close();
    }

    public Connection getConnection() {
        return connection;
    }
}
