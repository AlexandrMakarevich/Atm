package com.home.atm.command;

import com.home.atm.command.credit.RepayCreditCommand;
import com.home.atm.storage.Storage;
import com.home.atm.storageLoader.TestStorageLoaderJSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class TestRepayCreditCommand {

    private RepayCreditCommand repayCreditCommand;
    private TestStorageLoaderJSON testStorageLoaderJSON;
    private Storage storage;

    @Before
    public void init() {
        testStorageLoaderJSON = new TestStorageLoaderJSON();
        storage = testStorageLoaderJSON.createStorage();
        storage.setCurrentAccount("Petrov");
    }

    @Test
    public void testRepayIfCredit100() {
        Integer actualValueState = storage.getCreditStorage().get("USD");
        repayCreditCommand = new RepayCreditCommand("USD", 100);
        repayCreditCommand.execute(storage);
        Integer expectedValueState = storage.getCreditStorage().get("USD") + 100;
        Assert.assertEquals("Actual result must be expected",expectedValueState, actualValueState);
    }

    @Test
    public void testTryRepayBiggerThanNeed() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        repayCreditCommand = new RepayCreditCommand("USD", 200);
        repayCreditCommand.execute(storage);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        Assert.assertEquals("Actual","You are trying to pay more than you need !\n", allWrittenLines);
    }
}
