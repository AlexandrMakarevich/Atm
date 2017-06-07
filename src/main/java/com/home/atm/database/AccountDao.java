package com.home.atm.database;

import com.google.common.base.Optional;

public interface AccountDao {

    Optional<Account> findAccountByName(String accountInput);
}
