package com.home.atm.database;

import com.google.common.base.Optional;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

@Service("dbServiceAccount")
public class DbServiceAccount {

    private DbProcessAccount dbProcessAccount = new DbProcessAccount();

    @Resource(name = "dbService")
    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }

    private DbService dbService = new DbService();
    private static final Logger LOGGER = Logger.getLogger(DbServiceAccount.class);

    public void launchAccount() throws SQLException {
        System.out.println("Enter account name :");
        Scanner scanner = new Scanner(System.in);
        String account = scanner.next();
        method(1);
//        Optional<Integer> accountExist = dbProcessAccount.processAccount(connection, account);
//        if (!accountExist.isPresent()) {
//            System.out.println("Account doesn't exist!");
//            LOGGER.info("Account doesn't exist!");
//        }
//        while (accountExist.isPresent()) {
//           method(accountExist.get());
//        }
    }

    public void method(int accountId) throws SQLException {
        try {
            dbService.procesInput(accountId);
        }
        catch(IllegalArgumentException ex) {
            System.out.println("An error has occurred. " + ex.getMessage());
        }
    }
}
