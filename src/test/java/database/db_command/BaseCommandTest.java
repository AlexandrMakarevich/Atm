package database.db_command;

import com.home.atm.database.DataSource;
import com.home.atm.storage.LoaderProperty;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseCommandTest {

    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(BaseCommandTest.class);

    public BaseCommandTest() {
        LoaderProperty loaderProperty = new LoaderProperty();
        try {
            loaderProperty.loadProperty();
        } catch (Exception e) {
            LOGGER.warn("Error during loading properties", e);
            throw new RuntimeException("Error during loading properties", e);
        }
        connection = new DataSource().getConnection();
    }

    public void cleanTable(String... tables) {
        try {
            Statement statement = connection.createStatement();
            for (String table : tables) {
                statement.executeUpdate("truncate table " + table);
            }
            statement.close();
        } catch (SQLException e) {
            LOGGER.warn("Error during table clean up", e);
            throw new RuntimeException("Error during table clean up", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
