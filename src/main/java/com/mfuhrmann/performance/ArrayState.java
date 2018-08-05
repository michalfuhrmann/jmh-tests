package com.mfuhrmann.performance;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(Scope.Thread)
public class ArrayState {
    public List<String> testList;
    public List<String> smallList;
    public List<Integer> intList;
    public List<String> arrayList;
    public List<String> linkedList;

    public ArrayState() {
        arrayList = IntStream.range(0, 100_000).boxed().map(String::valueOf).collect(Collectors.toCollection(ArrayList::new));
        linkedList = IntStream.range(0, 100_000).boxed().map(String::valueOf).collect(Collectors.toCollection(LinkedList::new));
        testList = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            testList.add(String.valueOf(i));
        }
        Collections.shuffle(testList);

        smallList = testList.stream().limit(100).collect(Collectors.toCollection(ArrayList::new));

        intList = IntStream.range(0, 1_000_000).boxed().collect(Collectors.toCollection(ArrayList::new));
    }
}
