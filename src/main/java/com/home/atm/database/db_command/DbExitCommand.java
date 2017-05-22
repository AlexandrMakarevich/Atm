package com.home.atm.database.db_command;

import java.sql.SQLException;

public class DbExitCommand implements DbCommand{

    @Override
    public void executeDb(int accountName) throws SQLException {
        System.out.println("Get command close.");
        System.exit(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
