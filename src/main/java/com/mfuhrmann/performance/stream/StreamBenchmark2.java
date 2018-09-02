package com.mfuhrmann.performance.stream;

import com.mfuhrmann.performance.DefaultBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Fork(value = 1, warmups = 0)
public class StreamBenchmark2 extends DefaultBenchmark {

    @Benchmark
    public List<Long> testFor() {

        List<Long> list = new ArrayList<>(10_000_000);

        for (int i = 0; i < 10_000_000; i++) {
            list.add(ThreadLocalRandom.current().nextLong());
        }


        return list;

    }


    @Benchmark
    public List<Long> testStream() {

        return Stream.generate(() -> ThreadLocalRandom.current().nextLong())
                .limit(10_000_000)
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<Long> testStreamParallel() {

        return Stream.generate(() -> ThreadLocalRandom.current().nextLong())
                .limit(10_000_000)
                .parallel()
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> testStreamParallelUnoreded () {

        return Stream.generate(() -> ThreadLocalRandom.current().nextLong())
                .unordered()
                .limit(10_000_000)
                .parallel()
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> testStreamParallelBefore() {

        return Stream.generate(() -> ThreadLocalRandom.current().nextLong())
                .parallel()
                .limit(10_000_000)
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> testStreamParallelRange() {

        return IntStream.range(0, 10_000_000)
                .parallel()
                .mapToObj(value -> ThreadLocalRandom.current().nextLong())
                .collect(Collectors.toList());

    }

    @Benchmark
    public List<Long> testStreamParallelRangeUnordered() {

        return IntStream.range(0, 10_000_000)
                .unordered()
                .parallel()
                .mapToObj(value -> ThreadLocalRandom.current().nextLong())
                .collect(Collectors.toList());

    }
}