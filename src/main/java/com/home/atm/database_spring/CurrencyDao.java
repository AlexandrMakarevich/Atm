package com.home.atm.database_spring;

public interface CurrencyDao {

    void insertCurrency(String currencyName);

    Currency findCurrencyByName(String currencyNAme);
}
