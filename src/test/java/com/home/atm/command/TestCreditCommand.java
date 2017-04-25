package com.home.atm.command;

import com.home.atm.storage.Storage;
import com.home.atm.storageLoader.TestStorageLoaderJSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class TestCreditCommand {

    private CreditCommand creditCommand;
    private TestStorageLoaderJSON testStorageLoaderJSON;
    private Storage storage;
    private ByteArrayOutputStream bo;

    @Before
    public void init() {
        testStorageLoaderJSON = new TestStorageLoaderJSON();
        storage = testStorageLoaderJSON.createStorage();
        storage.setCurrentAccount("Petrov");
        bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
    }

    @Test
    public void testTakeCreditBigger1000() throws IOException {
        creditCommand = new CreditCommand("USD", 1200);
        executeAndAssert("Sorry you can take credit not bigger than 1000 in any currency.\n\n");
    }

    @Test
    public void testTakeFirstCredit() throws IOException {
        creditCommand = new CreditCommand("RUB", 100);
        executeAndAssert("You take credit 100 in currency RUB.\n");
    }

    @Test
    public void testTakeFirstCredit2() {
        creditCommand = new CreditCommand("RUB", 200);
        creditCommand.execute(storage);
        Integer actualResult = storage.getCreditStorage().get("RUB");
        Integer expectedResult = 200;
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    @Test
    public void testSumAllCreditsNotBiggerThousand() {
        Integer storageStateBeforeCredit = storage.getCreditStorage().get("USD");
        creditCommand = new CreditCommand("USD", 950);
        creditCommand.execute(storage);
        Integer storageStateAfterCredit = storage.getCreditStorage().get("USD");
        Assert.assertEquals("Actual result must be expected", storageStateAfterCredit, storageStateBeforeCredit);
    }

    @Test
    public void testTakeCreditToThousands() {
        Integer storageStateBeforeCredit = storage.getCreditStorage().get("USD");
        creditCommand = new CreditCommand("USD", 400);
        creditCommand.execute(storage);
        Integer actualResult = storage.getCreditStorage().get("USD");
        Integer expectedResult = storageStateBeforeCredit + 400;
        Assert.assertEquals("Actual result must be expected", expectedResult, actualResult);
    }

    public void executeAndAssert(String message) throws IOException {
        creditCommand.execute(storage);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        Assert.assertEquals("Actual", message, allWrittenLines);
    }
}
