package com.home.atm.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StorageLoader {

    public Storage loadStorage(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        Map<String, Integer> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] result = scanner.nextLine().split(" ");
            map.put(result[0], Integer.valueOf(result[1]));
        }
        return new Storage(map);
    }
}
