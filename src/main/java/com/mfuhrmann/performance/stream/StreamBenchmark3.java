package com.mfuhrmann.performance.stream;

import com.mfuhrmann.performance.ArrayState;
import com.mfuhrmann.performance.DefaultBenchmark;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Fork(value = 1, warmups = 0)
@State(Scope.Thread)
@Measurement(iterations = 10)
public class StreamBenchmark3 extends DefaultBenchmark {


    public static void main(String[] args) {

        Stream<Integer> integerStream = Stream.of(1, 2, 3);


        integerStream.forEach(System.out::println);

        integerStream.forEach(System.out::println);
    }



//    @Benchmark
//    public List<Integer> distinct(ArrayState arrayState) {
//
//        return arrayState.wrapperList.stream()
//                .map(ArrayState.Wrapper::getValue)
//                .distinct()
//                .collect(Collectors.toList());
//    }
//
//    @Benchmark
//    public Collection<Integer> set(ArrayState arrayState) {
//
//        return arrayState.wrapperList.stream()
//                .map(ArrayState.Wrapper::getValue)
//                .collect(Collectors.toSet());
//
//    }
//
//    @Benchmark
//    public List<Integer> distinct1Percent(ArrayState arrayState) {
//
//        return arrayState.wrapperList1PercentDuplicates.stream()
//                .map(ArrayState.Wrapper::getValue)
//                .distinct()
//                .collect(Collectors.toList());
//    }
//
//    @Benchmark
//    public Collection<Integer> set1Percent(ArrayState arrayState) {
//
//        return arrayState.wrapperList1PercentDuplicates.stream()
//                .map(ArrayState.Wrapper::getValue)
//                .collect(Collectors.toSet());
//
//    }
//
//
//    @Benchmark
//    public List<Integer> distinct10Percent(ArrayState arrayState) {
//
//        return arrayState.wrapperList10PercentDuplicates.stream()
//                .map(ArrayState.Wrapper::getValue)
//                .distinct()
//                .collect(Collectors.toList());
//    }
//
//    @Benchmark
//    public Collection<Integer> set10Percent(ArrayState arrayState) {
//
//        return arrayState.wrapperList10PercentDuplicates.stream()
//                .map(ArrayState.Wrapper::getValue)
//                .collect(Collectors.toSet());
//
//    }
//
//    @Benchmark
//    public List<Integer> distinct33Percent(ArrayState arrayState) {
//
//        return arrayState.wrapperList33PercentDuplicates.stream()
//                .map(ArrayState.Wrapper::getValue)
//                .distinct()
//                .collect(Collectors.toList());
//    }
//
//    @Benchmark
//    public Collection<Integer> set33Percent(ArrayState arrayState) {
//
//      return arrayState.wrapperList33PercentDuplicates.stream()
//                .map(ArrayState.Wrapper::getValue)
//                .collect(Collectors.toSet());
//
//    }


    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public List<Integer> distinct50Percent(ArrayState arrayState) {

        return arrayState.wrapperList50PercentDuplicates.stream()
                .map(ArrayState.Wrapper::getValue)
                .distinct()
                .collect(Collectors.toList());

    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public Collection<Integer> set50Percent(ArrayState arrayState) {

       return arrayState.wrapperList50PercentDuplicates.stream()
                .map(ArrayState.Wrapper::getValue)
                .collect(Collectors.toSet());

    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public Collection<Integer> set50PercentParalelle(ArrayState arrayState) {

        return arrayState.wrapperList50PercentDuplicates.stream()
                .parallel()
                .unordered()
                .distinct()
                .map(ArrayState.Wrapper::getValue)
                .collect(Collectors.toList());


    }




}