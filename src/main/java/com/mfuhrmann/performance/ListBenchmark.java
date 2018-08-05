package com.mfuhrmann.performance;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.List;

public class ListBenchmark {


    @Benchmark
    public List<String> arrayListRemoveFromMiddle(ArrayState state) {
        int i = state.arrayList.size() / 2;
        while (i > 0) {
            state.arrayList.remove(i--);
        }

        return state.arrayList;
    }


    @Benchmark
    public List<String> linkedLististRemoveFromMiddle(ArrayState state) {
        int i = state.linkedList.size() / 2;
        while (i > 0) {
            state.linkedList.remove(i--);
        }

        return state.linkedList;
    }

}
