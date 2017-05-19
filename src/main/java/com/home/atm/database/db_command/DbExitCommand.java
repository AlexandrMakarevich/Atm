package com.home.atm.database.db_command;

import java.sql.SQLException;

public class DbExitCommand implements DbCommand{

    @Override
    public void executeDb(int accountName) throws SQLException {
        System.out.println("Get command close.");
        System.exit(0);
    }
}
