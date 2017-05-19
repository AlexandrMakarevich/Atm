package com.home.atm.database;

import com.home.atm.storage.LoaderProperty;
import java.io.IOException;
import java.sql.SQLException;

public class AtmDbLauncher {

    public static void main(String[] args) throws SQLException, IOException {
        LoaderProperty loaderProperty = new LoaderProperty();
        loaderProperty.loadProperty();
        DbServiceAccount dbServiceAccount = new DbServiceAccount();
        dbServiceAccount.launchAccount();
    }
}
