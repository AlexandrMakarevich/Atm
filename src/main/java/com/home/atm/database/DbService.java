package com.home.atm.database;

import com.home.atm.database.db_command.DbCommand;
import com.home.atm.database.db_parser.DbDelegatedInputParser;
import java.sql.SQLException;
import java.util.Scanner;

public class DbService {

    private DbDelegatedInputParser dbInputParser = new DbDelegatedInputParser();

    public void procesInput(int accountId) throws SQLException {
        System.out.println("Enter operation :");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        DbCommand dbCommand = dbInputParser.defaultParseInput(input);
        if (dbCommand == null) {
            System.out.println("You enter wrong command!\nTry again :");
        } else {
            dbCommand.executeDb(accountId);
        }
    }
}
