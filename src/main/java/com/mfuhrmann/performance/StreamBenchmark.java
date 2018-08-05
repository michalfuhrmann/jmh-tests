package com.mfuhrmann.performance;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamBenchmark {
    @Benchmark
    public List<String> testFor(ArrayState state) {
        List<String> results = new ArrayList<>();

        for (int i = 0; i < state.testList.size(); i++) {
            String s = state.testList.get(i);

            if (s.length() > 5) {
                results.add("Value: " + s);
            }
        }

        results.sort(String::compareTo);
        return results;
    }

    @Benchmark
    public List<String> testStream(ArrayState state) {
        return state.testList
                .stream()
                .filter(s -> s.length() > 5)
                .map(s -> "Value: " + s)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> testStreamParrallel(ArrayState state) {
        return state.testList
                .stream()
                .parallel()
                .filter(s -> s.length() > 5)
                .map(s -> "Value: " + s)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> testStreamInLoop(ArrayState state) {

        return IntStream.range(0, 1000).mapToObj(i -> state.smallList
                .stream()
                .filter(s -> s.length() > 2)
                .map(s -> "Value: " + s))
                .flatMap(stringStream -> stringStream)
                .collect(Collectors.toList());


    }

    @Benchmark
    public List<String> testForInLoop(ArrayState state) {
        List<String> output = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            for (String s : state.smallList) {
                if (s.length() > 2) {
                    output.add("Value: " + s);
                }
            }
        }
        return output;
    }

    @Benchmark
    public ArrayList<Double> testLargeStreamParallel(ArrayState state) {
        return state.intList
                .stream()
                .map(i -> Math.sqrt(i * 2 / ((i >> 3) + 1)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Benchmark
    public List<Double> testLargeStream(ArrayState state) {
        List<Double> collect = new ArrayList<>();
        for (Integer i : state.intList) {
            collect.add(Math.sqrt(i * 2 / ((i >> 3) + 1)));
        }
        return collect;
    }


}

