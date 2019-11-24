package com.github.notyy.example;

import io.vavr.Tuple;
import io.vavr.Tuple2;

public class Split {
    public Tuple2<Integer,Integer> execute(String param1) {
        String[] splits = param1.split(",");
        Integer input = Integer.valueOf(splits[0]);
        Integer accu = Integer.valueOf(splits[1]);
        return Tuple.of(input,accu);
    }
}
