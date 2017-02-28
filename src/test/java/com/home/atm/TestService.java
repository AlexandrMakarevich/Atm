package com.home.atm;


import com.home.atm.storage.Storage;
import org.junit.Assert;
import org.junit.Test;

public class TestService {

   private Service service;

    @Test
    public void testAddRub100() {
        Storage storage = new Storage();
        service = new Service(storage);
        service.processInput("+ RUB 150");
        Integer actualResult = storage.getStorage().get("RUB");
        Integer expectedResult = 150;
        Assert.assertEquals("Actual result must be expected", expectedResult,actualResult);
    }

    @Test
    public void addSameCurrencyTwice() {
        Storage storage = new Storage();
        service = new Service(storage);
        service.processInput("+ RUB 130");
        service.processInput("+ RUB 130");
        Integer actualResult = storage.getStorage().get("RUB");
        Integer expectedResult = 260;
        Assert.assertEquals("Actual result must be expected", expectedResult,actualResult);
    }

}
