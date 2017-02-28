package com.home.atm;

import com.home.atm.command.Command;
import com.home.atm.storage.Storage;

import java.util.Scanner;

public class Service {

    private InputParser inputPars = new InputParser();
    private Storage storage;

    public Service(Storage storage) {
        this.storage = storage;
    }

    public void procesInput() {
        System.out.println("¬ведите операцию :");
        Scanner in = new Scanner(System.in);
        String inputString = in.nextLine();
        this.processInput(inputString);
    }

    public void processInput(String inputString ) {
        Command command = inputPars.parseInput(inputString);
        command.execute(storage);
    }
}
