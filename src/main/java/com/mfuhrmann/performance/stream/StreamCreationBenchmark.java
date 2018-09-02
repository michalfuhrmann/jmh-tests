package com.mfuhrmann.performance.stream;

import com.mfuhrmann.performance.ArrayState;
import com.mfuhrmann.performance.DefaultBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Fork(value = 1, warmups = 0)
public class StreamCreationBenchmark extends DefaultBenchmark {


    @Benchmark
    public List<String> testConcat(ArrayState state) {

        return Stream.concat(state.testList.stream(), state.testList.stream())
                .collect(Collectors.toList());

    }


    @Benchmark
    public List<String> testStreamOf(ArrayState state) {

        return Stream.of(state.testList.stream(), state.testList.stream())
                .flatMap(Function.identity())
                .collect(Collectors.toList());

    }


    @Benchmark
    public List<String> testConcatList(ArrayState state) {

        List<String> list = new ArrayList<>();
        list.addAll(state.testList.stream().collect(Collectors.toList()));
        list.addAll(state.testList.stream().collect(Collectors.toList()));
        return list;
    }


}

