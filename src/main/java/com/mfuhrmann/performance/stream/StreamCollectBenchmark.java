package com.mfuhrmann.performance.stream;

import com.mfuhrmann.performance.ArrayState;
import com.mfuhrmann.performance.DefaultBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Fork(value = 1, warmups = 0)
public class StreamCollectBenchmark extends DefaultBenchmark {


    @Benchmark
    public List<Integer> testDoubleStream(ArrayState state) {
        return state.testList
                .stream()
                .collect(collectingAndThen(groupingBy(String::length),
                        integersMap -> integersMap.entrySet().stream()
                                .map(integerListEntry -> integerListEntry.getKey())
                                .collect(Collectors.toList())));
    }



    @Benchmark
    public Map<Integer, List<String>> testDoubleStrea2(ArrayState state) {
        return state.testList
                .stream()
                .collect(groupingBy(String::length, mapping(o -> o, toList())));
    }


    @Benchmark
    public List<Integer> testCollectAndThen(ArrayState state) {
        return state.testList
                .stream()
                .collect(groupingBy(String::length))
                .entrySet().stream()
                .map(integerListEntry -> integerListEntry.getKey())
                .collect(Collectors.toList());
    }


}

