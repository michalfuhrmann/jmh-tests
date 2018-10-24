package com.mfuhrmann.performance.collections;

import com.mfuhrmann.performance.DefaultBenchmark;
import org.openjdk.jmh.annotations.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Fork(value = 1, warmups = 0)
@Measurement(iterations = 10)
 public class ListVsSetContains extends DefaultBenchmark {

    @Benchmark
    public List<Long> m0_containsListVerySmall(State state) {

        return state.verySmallLongsCopy.stream()
                .filter(state.verySmallLongs::contains)
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> m2_containsListSmall(State state) {

        return state.smallLongsCopy.stream()
                .filter(state.smallLongs::contains)
                .collect(Collectors.toList());

    }


    @Benchmark
    public List<Long> m4_containsListMedium(State state) {

        return state.mediumLongsCopy.stream()
                .filter(state.mediumLongs::contains)
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> m6_containsListHigh(State state) {

        return state.highLongsCopy.stream()
                .filter(state.highLongs::contains)
                .collect(Collectors.toList());

    }


    @Benchmark
    public List<Long> m1_containsSetVerySmall(State state) {

        Set<Long> longs = new HashSet<>(state.verySmallLongsCopy);

        return state.verySmallLongsCopy.stream()
                .filter(longs::contains)
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> m3_containsSetSmall(State state) {

        Set<Long> longs = new HashSet<>(state.smallLongs);

        return state.smallLongsCopy.stream()
                .filter(longs::contains)
                .collect(Collectors.toList());

    }


    @Benchmark
    public List<Long> m5_containsSetMedium(State state) {

        Set<Long> longs = new HashSet<>(state.mediumLongs);

        return state.mediumLongsCopy.stream()
                .filter(longs::contains)
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> m7_containsSetHigh(State state) {

        Set<Long> longs = new HashSet<>(state.highLongs);

        return state.highLongsCopy.stream()
                .filter(longs::contains)
                .collect(Collectors.toList());

    }

}
