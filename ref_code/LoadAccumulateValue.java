package com.github.notyy.example;

import java.io.*;

public class LoadAccumulateValue {
    public Integer execute() {
        //TODO give me path
        File file = new File("./localoutput/accu.txt");
        BufferedReader reader = null;
        try {
            //using append mode, if you need overwritten mode, set false.
            reader = new BufferedReader(new FileReader(file));
            String s = reader.readLine();
            if(s == null || s.trim().isEmpty()) {
                return 0;
            }else{
                return Integer.valueOf(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
}
