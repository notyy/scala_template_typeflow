package com.github.notyy.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SaveAccumulateValue {
    public void execute(Integer param1) {
        //TODO give me path
        Path file = Paths.get("./localoutput/accu.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.WRITE)) {
            writer.write(param1.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
