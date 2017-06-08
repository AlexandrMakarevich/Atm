package database.db_command;

import com.home.atm.database.db_command.DbAddCommand;
import com.home.atm.database.db_command.DbPrintBalance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;

public class IntTestDbPrintBalance extends BaseCommandTest {

    private DbPrintBalance dbPrintBalance;
    private DbAddCommand dbAddCommand;
    private String currency = "rub";
    private int amount = 20;
    private int accountId = 1;

//    @Before
//    public void init() throws IOException{
//        dbAddCommand = new DbAddCommand(currency, amount);
//        dbPrintBalance = new DbPrintBalance();
//    }
//
//    @Test
//    public void testDbPrintBalance() throws SQLException, IOException{
//        cleanTable("debit");
//        dbAddCommand.executeDb(accountId);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(baos));
//        dbPrintBalance.executeDb(accountId);
//        baos.flush();
//        String actualResult = new String(baos.toByteArray());
//        Assert.assertEquals("Actual", "Your balance is 20 in currency rub.\n", actualResult);
//    }
}
