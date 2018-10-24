package com.mfuhrmann.performance;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(Scope.Thread)
@Warmup(iterations = 5)
public class ArrayState {
    public List<String> testList;
    public List<String> smallList;
    public List<Integer> intList;
    public List<String> arrayList;
    public List<String> linkedList;
    public List<Wrapper> wrapperList;
    public List<Wrapper> wrapperList1PercentDuplicates;
    public List<Wrapper> wrapperList10PercentDuplicates;
    public List<Wrapper> wrapperList33PercentDuplicates;
    public List<Wrapper> wrapperList50PercentDuplicates;

    public double[] values = new double[2000000];


    public ArrayState() {
        IntStream.range(0, values.length).forEach(value -> values[value] = value % 1000);
        arrayList = IntStream.range(0, 100_000).boxed().map(String::valueOf).collect(Collectors.toCollection(ArrayList::new));
        linkedList = IntStream.range(0, 100_000).boxed().map(String::valueOf).collect(Collectors.toCollection(LinkedList::new));
        testList = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            testList.add(String.valueOf(i));
        }
        Collections.shuffle(testList);

        smallList = testList.stream().limit(100).collect(Collectors.toCollection(ArrayList::new));

        intList = IntStream.range(0, 1_000_000).boxed().collect(Collectors.toCollection(ArrayList::new));

        wrapperList = IntStream.range(0, 1000)
                .mapToObj(Wrapper::new)
                .collect(Collectors.toList());

        wrapperList1PercentDuplicates = IntStream.range(0, 1000)
                .map(i -> i % 100)
                .mapToObj(Wrapper::new)
                .collect(Collectors.toList());

        wrapperList10PercentDuplicates = IntStream.range(0, 1000)
                .map(i -> i % 10)
                .mapToObj(Wrapper::new)
                .collect(Collectors.toList());
        wrapperList33PercentDuplicates = IntStream.range(0, 1000)
                .map(i -> i % 3)
                .mapToObj(Wrapper::new)
                .collect(Collectors.toList());
        wrapperList50PercentDuplicates = IntStream.range(0, 30_000_000)
                .map(i -> i % 2)
                .mapToObj(Wrapper::new)
                .collect(Collectors.toList());
    }

    public static class Wrapper {
        private final int value;

        public Wrapper(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
