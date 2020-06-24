package com.github.notyy.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadAccumulateValue {
    public Integer execute() {
        Path file = Paths.get("./localoutput/accu.txt");

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String s = reader.readLine();
            if (s == null || s.trim().isEmpty()) {
                return 0;
            } else {
                return Integer.valueOf(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
