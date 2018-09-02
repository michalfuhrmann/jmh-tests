package com.mfuhrmann.performance.stream;

import com.mfuhrmann.performance.ArrayState;
import com.mfuhrmann.performance.DefaultBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Fork(value = 1, warmups = 0)
public class StreamCollectionBenchmark extends DefaultBenchmark {


    @Benchmark
    public Set<String> testStreamToSet(ArrayState state) {
        return state.testList.stream()
                .collect(toSet());
    }


    @Benchmark
    public List<String> testStreamToList(ArrayState state) {
        return new ArrayList<>(state.testList.stream()
                .collect(toSet()));
    }



    @Benchmark
    public List<String> testDistinct(ArrayState state) {
        return state.testList.stream()
                .distinct()
                .collect(toList());
    }



}

