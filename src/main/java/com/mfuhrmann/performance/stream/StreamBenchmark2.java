package com.mfuhrmann.performance.stream;

import com.mfuhrmann.performance.DefaultBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Fork(value = 1, warmups = 0)
@State(Scope.Thread)
public class StreamBenchmark2 extends DefaultBenchmark {


    private final LongAdder adder = new LongAdder();

    @Benchmark
    public List<Long> testFor() {

        List<Long> list = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            list.add(getLong());
        }

        return list;

    }


    @Benchmark
    public List<Long> testStream() {

        return Stream.generate(() -> getLong())
                .limit(10_000_000)
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<Long> testStreamParallel() {

        return Stream.generate(() -> getLong())
                .limit(10_000_000)
                .parallel()
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> testStreamParallelUnoreded() {

        return Stream.generate(() -> getLong())
                .unordered()
                .limit(10_000_000)
                .parallel()
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> testStreamParallelBefore() {

        return Stream.generate(() -> getLong())
                .parallel()
                .limit(10_000_000)
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> testStreamParallelRange() {

        return IntStream.range(0, 10_000_000)
                .parallel()
                .mapToObj(value -> getLong())
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> testStreamParallelRangeUnordered() {

        return IntStream.range(0, 10_000_000)
                .unordered()
                .parallel()
                .mapToObj(value -> getLong())
                .collect(Collectors.toList());

    }

    @Benchmark
    public Collection<Long> testStreamParallelRangeUnorderedCollection() {

        return IntStream.range(0, 10_000_000)
                .unordered()
                .parallel()
                .mapToObj(value -> getLong())
                .collect(Collectors.toSet());

    }

    private long getLong() {
        adder.increment();
        return adder.sumThenReset();
    }
}