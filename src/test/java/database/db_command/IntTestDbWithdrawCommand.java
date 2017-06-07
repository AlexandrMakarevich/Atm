package database.db_command;

import com.google.common.base.Optional;
import com.home.atm.database.db_command.DbAddCommand;
import com.home.atm.database.db_command.DbWithdrawCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IntTestDbWithdrawCommand extends BaseCommandTest {

    private DbWithdrawCommand dbWithdrawCommand;
    private DbAddCommand dbAddCommand;
    private String currency = "rub";
    private int accountId = 1;

    @Before
    public void init() throws IOException {
        cleanTable("debit");
        cleanTable("currency");
        //insertCurrency();
    }

    @Rule
    public ExpectedException testRuleException = ExpectedException.none();

    @Test
    public void testNotEnoughMoney() throws SQLException, IOException {
        int addAmount = 40;
        int withdrawAmount = 150;
        dbAddCommand = new DbAddCommand(currency, addAmount);
        dbAddCommand.executeDb(accountId);
        dbWithdrawCommand = new DbWithdrawCommand(currency, withdrawAmount);
        String expectedResult = "Not enough money on the account!\n";
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        dbWithdrawCommand.executeDb(accountId);
        bo.flush();
        String actualResult = new String(bo.toByteArray());
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testWhereCurrencyNotExist() throws SQLException, IOException {
        dbWithdrawCommand = new DbWithdrawCommand(currency, 100);
        cleanTable("currency");
        String expectedResult = "You don't have money on currency " + currency + "\n";
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        dbWithdrawCommand.executeDb(accountId);
        bo.flush();
        String actualResult = new String(bo.toByteArray());
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

//    @Test
//    public void testCheckCurrency() throws SQLException {
//        dbWithdrawCommand = new DbWithdrawCommand(currency, 100);
//        Integer expectedResult = 1;
//        Optional<Integer> actualResult = dbWithdrawCommand.checkCurrency(getConnection(), currency);
//        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult.get());
//    }
//
//    private void insertCurrency() {
//        String query = "insert into currency (currency_name) values ('rub'),('usd'),('eur')";
//        try {
//            Statement statement = getConnection().createStatement();
//            statement.executeUpdate(query);
//            statement.close();
//        } catch (SQLException e) {
//            throw new RuntimeException("Not have connection", e);
//        }
//    }

    @Test
    public void testGetBalanceWhereBalanceZero() throws SQLException {
        dbWithdrawCommand = new DbWithdrawCommand(currency, 100);
        this.testRuleException.expect(IllegalStateException.class);
        this.testRuleException.expectMessage("You don't have money on this currency!");
        dbWithdrawCommand.executeDb(accountId);
    }

//    @Test
//    public void testGetBalance() throws SQLException {
//        int addAmount = 100;
//        int withdrawAmount = 15;
//        dbAddCommand = new DbAddCommand(currency, addAmount);
//        dbWithdrawCommand = new DbWithdrawCommand(currency, withdrawAmount);
//        dbAddCommand.executeDb(accountId);
//        int expectedResult = addAmount;
//        Optional<Integer> currencyId = dbWithdrawCommand.checkCurrency(getConnection(), currency);
//        int actualResult = dbWithdrawCommand.getBalance(accountId, currencyId.get());
//        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
//    }
//
//    @Test
//    public void testWithdrawProcess() throws SQLException {
//        int addAmount = 100;
//        int withdrawAmount = 42;
//        dbAddCommand = new DbAddCommand(currency, addAmount);
//        dbWithdrawCommand = new DbWithdrawCommand(currency, withdrawAmount);
//        dbAddCommand.executeDb(accountId);
//        int expectedResult = addAmount - withdrawAmount;
//        Optional<Integer> currencyId = dbWithdrawCommand.checkCurrency(getConnection(), currency);
//        dbWithdrawCommand.withdrawProcesss(currencyId.get(), accountId);
//        int actualResult = dbWithdrawCommand.getBalance(accountId, currencyId.get());
//        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
//    }
//
//    @Test
//    public void testWithdrawExecute() throws SQLException, IOException {
//        int addAmount = 100;
//        int withdrawAmount = 73;
//        dbAddCommand = new DbAddCommand(currency, addAmount);
//        dbWithdrawCommand = new DbWithdrawCommand(currency, withdrawAmount);
//        dbAddCommand.executeDb(accountId);
//        dbWithdrawCommand.executeDb(accountId);
//        int expectedResult = addAmount - withdrawAmount;
//        int actualResult = checkBalance();
//        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
//    }
//
//    public int checkBalance() throws SQLException {
//        String query = "select balance from debit d " +
//                " inner join currency c on c.id = d.currency_id " +
//                "where account_id = ? and c.currency_name = ?";
//        PreparedStatement prepStatement = getConnection().prepareStatement(query);
//        prepStatement.setInt(1, accountId);
//        prepStatement.setString(2, currency);
//        ResultSet resultSet = prepStatement.executeQuery();
//        if (!resultSet.next()) {
//            Assert.fail("Balance on this currency not found");
//        }
//        return resultSet.getInt(1);
//    }
}
