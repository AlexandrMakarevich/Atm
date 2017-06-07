package com.home.atm.database_spring;

import com.home.atm.database.DbServiceAccount;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.sql.SQLException;

public class AtmSpringLauncher {

    public static void main(String[] args) throws SQLException {

        String[] configs = {"/atm-spring.xml"};
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configs);
        DbServiceAccount dbServiceAccount = (DbServiceAccount) applicationContext.getBean("dbServiceAccount");
        dbServiceAccount.launchAccount();
//        Currency currency = currencyDao.findCurrencyByName("usd");
//        System.out.println(currency);
    }
}