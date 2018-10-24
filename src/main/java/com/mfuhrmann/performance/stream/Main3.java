package com.mfuhrmann.performance.stream;

import com.google.common.base.Stopwatch;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main3 {


    public static void main(String[] args) throws InterruptedException {
        List<Obj> collect = IntStream.range(0, 6_041_979)
                .mapToDouble(x -> x % 100)
                .mapToObj(Obj::new)
                .collect(Collectors.toList());

        Collections.shuffle(collect);


        System.gc();
        System.out.println("sleeping");
        Thread.sleep(3000);

        Stopwatch stopwatch = Stopwatch.createStarted();
        collect.sort(Comparator.comparingDouble(value -> value.score));

//        Optional<Obj> first = collect.stream()
//                .sorted(Comparator.comparingDouble(value -> value.score))
//                .findFirst();

        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        System.out.println(collect.get(0).score);

    }

    static class Obj {
        private final double score;

        Obj(double score) {
            this.score = score;
        }
    }

}
