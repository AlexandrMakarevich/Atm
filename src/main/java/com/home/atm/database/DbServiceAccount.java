package com.home.atm.database;

import com.google.common.base.Optional;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Scanner;

@Service("dbServiceAccount")
public class DbServiceAccount {

    private AccountDaoImpl accountDaoImpl;
    private DbService dbService = new DbService();
    private static final Logger LOGGER = Logger.getLogger(DbServiceAccount.class);

    @Resource(name = "accountDaoImpl")
    public void setAccountDaoImpl(AccountDaoImpl accountDaoImpl) {
        this.accountDaoImpl = accountDaoImpl;
    }

    @Resource(name = "dbService")
    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }

    public void launchAccount() throws SQLException {
        System.out.println("Enter account name :");
        Scanner scanner = new Scanner(System.in);
        String account = scanner.next();
        Optional<Account> accountExist = accountDaoImpl.findAccountByName(account);
        if (!accountExist.isPresent()) {
            System.out.println("Account doesn't exist!");
            LOGGER.info("Account doesn't exist!");
        }
        while (accountExist.isPresent()) {
            method(accountExist.get().getAccountId());
        }
    }

    public void method(int accountId) throws SQLException {
        try {
            dbService.procesInput(accountId);
        } catch (IllegalArgumentException ex) {
            System.out.println("An error has occurred. " + ex.getMessage());
        }
    }
}
