package com.home.atm.database.db_command;

import java.sql.SQLException;

public interface DbCommand {

    void executeDb(int accountName) throws SQLException;
}
