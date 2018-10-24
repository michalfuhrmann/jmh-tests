package com.mfuhrmann.performance.collections;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Warmup;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@org.openjdk.jmh.annotations.State(Scope.Thread)
@Warmup(iterations = 5)
public class State {

    public final List<Long> verySmallLongs = LongStream.range(0, 10).boxed().collect(Collectors.toList());
    public final List<Long> smallLongs = LongStream.range(0, 100).boxed().collect(Collectors.toList());
    public final List<Long> mediumLongs = LongStream.range(0, 1000).boxed().collect(Collectors.toList());
    public final List<Long> highLongs = LongStream.range(0, 10_000).boxed().collect(Collectors.toList());


    public final List<Long> verySmallLongsCopy = LongStream.range(0, 10).boxed().collect(Collectors.toList());
    public final List<Long> smallLongsCopy = LongStream.range(0, 100).boxed().collect(Collectors.toList());
    public final List<Long> mediumLongsCopy = LongStream.range(0, 1000).boxed().collect(Collectors.toList());
    public final List<Long> highLongsCopy = LongStream.range(0, 10_000).boxed().collect(Collectors.toList());


}
