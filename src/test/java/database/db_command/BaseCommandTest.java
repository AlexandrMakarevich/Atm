package database.db_command;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import javax.annotation.Resource;

public class BaseCommandTest {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void cleanTable(String... tables) {
        for (String table : tables) {
            String query = "truncate table " + table;
            namedParameterJdbcTemplate.getJdbcOperations().update(query);
        }
    }

    public int insert(String tableName, String columnName, String value) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = String.format("insert into %s(%s) value(:p_value)", tableName, columnName);
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_value", value);
        int rowCount = namedParameterJdbcTemplate.update(query, namedParameters, keyHolder);
        if (rowCount == 0) {
            throw new IllegalStateException("No column has been changed!");
        }
        return keyHolder.getKey().intValue();
    }
}
