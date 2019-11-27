package com.github.notyy.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveAccumulateValue {
    public void execute(Integer param1) {
        //TODO give me path
        File file = new File("./localoutput/accu.txt");
        BufferedWriter writer = null;
        try {
            //using append mode, if you need overwritten mode, set false.
            writer = new BufferedWriter(new FileWriter(file, false));
            writer.write(param1.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
