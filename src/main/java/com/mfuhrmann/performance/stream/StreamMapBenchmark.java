package com.mfuhrmann.performance.stream;

import com.mfuhrmann.performance.ArrayState;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMapBenchmark {

    @Benchmark
    public List<String> multipleMappingss(ArrayState state) {
        return state.testList
                .stream()
                .map(s -> new Test("Value: " + s))
                .map(Test::getNested)
                .map(Nested::getS)
                .flatMap(Collection::stream)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> singleMapping(ArrayState state) {
        return state.testList
                .stream()
                .map(s -> new Test("Value :" + s))
                .flatMap(test -> test.getNested().getS().stream())
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }


    static class Test {
        private final Nested nested;


        Test(String s) {
            this.nested = new Nested(s);
        }

        public Nested getNested() {
            return nested;
        }
    }

    static class Nested {

        private final List<String> s;

        Nested(String s) {
            this.s = new LinkedList<>();
            this.s.add(s + "1");
            this.s.add(s + "2");
            this.s.add(s + "3");
        }

        public List<String> getS() {
            return s;
        }

    }
}

