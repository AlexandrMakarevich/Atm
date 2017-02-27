package com.home.atm.command;

import com.home.atm.InputParser;
import java.util.Scanner;

public class Service {

    private InputParser inputPars = new InputParser();
    private Storage stor = new Storage();

    public void procesInput() {
        System.out.println("¬ведите операцию :");
        Scanner in = new Scanner(System.in);
        String inputString = in.nextLine();
        Command command = inputPars.parseInput(inputString);
        command.execute(stor);

    }
}
