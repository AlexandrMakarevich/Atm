package com.home.atm.database;

import com.google.common.base.Optional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbProcessAccount {

    public Optional<Integer> processAccount(Connection connection, String accountInput) throws SQLException {
        String query = "select id, account_name from account where account_name = ?";
        PreparedStatement prepStatement = connection.prepareStatement(query);
        prepStatement.setString(1, accountInput);
        ResultSet resultSet = prepStatement.executeQuery();
        while (!resultSet.next()) {
            return Optional.absent();
        }
        return Optional.of(resultSet.getInt(1));
    }
}
