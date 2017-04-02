package com.home.atm;

import com.home.atm.storage.Storage;
import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class TestService {

    private Service service;
    private String usdCurrency = "USD";
    private String rubCurrency = "RUB";

    @Test
    public void testAddRub100() {
        Integer firstAmount = 100;
        Storage storage = createAccountStorage();
        service = new Service(storage);
        service.processInput(String.format("+ %s %d", rubCurrency, firstAmount));
        Integer actualResult = storage.getStorage().get(rubCurrency);
        Assert.assertEquals("Actual result must be expected", firstAmount, actualResult);
    }

    @Test
    public void addSameCurrencyTwice() {
        Integer firstAmount = 130;
        Storage storage = createAccountStorage();
        service = new Service(storage);
        service.processInput(String.format("+ %s %d", rubCurrency, firstAmount));
        service.processInput(String.format("+ %s %d", rubCurrency, firstAmount));
        Integer actualResult = storage.getStorage().get(rubCurrency);
        Integer expectedResult = 260;
        Assert.assertEquals("Actual result must be expected", expectedResult,actualResult);
    }

    @Test
    public void testWithdraw() {
        Integer firstAmount = 130;
        Integer secondAmount = 30;
        Storage storage = createAccountStorage();
        service = new Service(storage);
        service.processInput(String.format("+ %s %d", usdCurrency,firstAmount));
        service.processInput(String.format("- %s %d", usdCurrency,secondAmount));
        Integer actualResult = storage.getStorage().get(usdCurrency);
        Integer expectedResult = 100;
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testWithdrawIfBalanceZero() {
        Integer firstAmount = 0;
        Integer secondAmount = 100;
        Storage storage = createAccountStorage();
        service = new Service(storage);
        service.processInput(String.format("+ %s %d", usdCurrency,firstAmount));
        service.processInput(String.format("- %s %d", usdCurrency,secondAmount));
        service.processInput(String.format("+ %s %d", usdCurrency,firstAmount));
    }

    @Test
    public void testCorrectAddCurrency() {
        Integer firstAmount = 200;
        Integer secondAmount = 600;
        Storage storage = createAccountStorage();
        service = new Service(storage);
        service.processInput(String.format("+ %s %d", usdCurrency, firstAmount));
        service.processInput(String.format("+ %s %d", rubCurrency, secondAmount));
        service.processInput(String.format("+ %s %d", usdCurrency, firstAmount));
        Integer actualResult = storage.getStorage().get(rubCurrency);
        Assert.assertEquals("Actual result must be expected", secondAmount, actualResult);
    }

    @Test
    public void testWithdrawWhenStorageEmpty() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        Storage storage = createAccountStorage();
        service = new Service(storage);
        service.processInput("- USD 50");
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        Assert.assertEquals("Actual", "��� ������� �� �����!\r\n", allWrittenLines);
    }

    public Storage createAccountStorage() {
        String accountName = "Petrov";
        Map<String, Integer> petrov = new HashMap<>();
        Map<String, Map<String, Integer>> account = new HashMap<>();
        account.put(accountName, petrov);
        Storage storage = new Storage(account);
        storage.setCurrentAccount(accountName);
        return storage;
    }
}
