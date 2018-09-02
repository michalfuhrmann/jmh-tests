package com.mfuhrmann.performance;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.State;

import java.util.Arrays;
import java.util.List;

public class TestBenchmark {


    @Benchmark
    public double simpleLoop(ArrayState state) {
        double sum = 0;
        for (int i = 0; i < state.values.length; i++) {
            double x = (state.values[i] + 1.0) * 2.0 + 5.0;
            sum += x;
        }
        return sum;
    }

    @Benchmark
    public double complexLoop(ArrayState state) {
        double sum = 0;
        for (int i = 0; i < state.values.length; i++) {
            double x = (state.values[i] + 1.0);
            double y = x * 2.0;
            double z = y + 5.0;
            sum += z;
        }
        return sum;
    }


    @Benchmark
    public double mapReduce(ArrayState state) {
        return Arrays.stream(state.values)
                .map(x -> x + 1)
                .map(x -> x * 2)
                .map(x -> x + 5)
                .reduce(0, Double::sum);
    }


    @Benchmark
    public double singleMapReduce(ArrayState state) {
        return Arrays.stream(state.values)
                .map(x -> (x + 1) * 2 + 5)
                .reduce(0, Double::sum);
    }

    @Benchmark
    public double singleMapReduceDouble(ArrayState state) {
        return Arrays.stream(state.values)
                .map(x -> (x + 1) * 2.0 + 5.0)
                .reduce(0, Double::sum);
    }

    @Benchmark
    public double doubleStreamSum(ArrayState state) {
        return Arrays.stream(state.values)
                .map(x -> (x + 1) * 2 + 5)
                .sum();
    }

}
