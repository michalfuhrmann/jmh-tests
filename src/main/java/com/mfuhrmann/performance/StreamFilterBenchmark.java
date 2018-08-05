package com.mfuhrmann.performance;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamFilterBenchmark {
    private static final Predicate<String> predicate = getStringPredicate();
    private static final Predicate<String> predicat2 = getStringPredicate2();
    private static final Predicate<String> predicate3 = getStringPredicate3();
    private static final Predicate<String> predicate4 = getStringPredicate4();
    private static final Predicate<String> predicate5 = getStringPredicate5();
    private static final Predicate<String> predicate6 = getStringPredicate6();

    @Benchmark
    public List<String> multipleFilters(ArrayState state) {
        return state.testList
                .stream()
                .filter(s -> !s.isEmpty())
                .filter(s -> s.length() > 5)
                .filter(s -> s.charAt(3) != 'c')
                .filter(s -> s.charAt(1) != 'a')
                .filter(s -> s.charAt(2) != 'b')
                .filter(s -> s.length() < 10)
                .map(s -> "Value: " + s)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> singleFilter(ArrayState state) {
        return state.testList
                .stream()
                .filter(s -> !s.isEmpty() && s.length() > 5 && s.charAt(3) != 'c' && s.charAt(1) != 'a' && s.charAt(2) != 'b' && s.length() < 10)
                .map(s -> "Value: " + s)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    //    @Benchmark
//    public List<String> multipleFiltersParallel(ArrayState state) {
//        return state.testList
//                .stream()
//                .parallel()
//                .filter(s -> !s.isEmpty())
//                .filter(s -> s.length() > 5)
//                .filter(s -> s.charAt(3) != 'c')
//                .filter(s -> s.charAt(1) != 'a')
//                .filter(s -> s.charAt(2) != 'b')
//                .filter(s -> s.length() < 10)
//                .map(s -> "Value: " + s)
//                .sorted(String::compareTo)
//                .collect(Collectors.toList());
//    }
//
//    @Benchmark
//    public List<String> singleFilterPArallel(ArrayState state) {
//        return state.testList
//                .stream()
//                .parallel()
//                .filter(s -> !s.isEmpty() && s.length() > 5 && s.charAt(3) != 'c' && s.charAt(1) != 'a' && s.charAt(2) != 'b' && s.length() < 10)
//                .map(s -> "Value: " + s)
//                .sorted(String::compareTo)
//                .collect(Collectors.toList());
//    }
//
//
    @Benchmark
    public List<String> extractedPredicates(ArrayState state) {
        return state.testList
                .stream()
                .filter(getStringPredicate())
                .filter(getStringPredicate2())
                .filter(getStringPredicate3())
                .filter(getStringPredicate4())
                .filter(getStringPredicate5())
                .filter(getStringPredicate6())
                .map(s -> "Value: " + s)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> extractedPredicatesToFIeld(ArrayState state) {
        return state.testList
                .stream()
                .filter(predicate)
                .filter(predicat2)
                .filter(predicate3)
                .filter(predicate4)
                .filter(predicate5)
                .filter(predicate6)
                .map(s -> "Value: " + s)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

//
//    @Benchmark
//    public List<String> extractBoolean(ArrayState state) {
//        return state.testList
//                .stream()
//                .filter(s -> isaBoolean(s))
//                .filter(s -> isaBoolean2(s))
//                .filter(s -> isaBoolean3(s))
//                .filter(s -> isaBoolean4(s))
//                .filter(s -> isaBoolean5(s))
//                .filter(s -> isaBoolean6(s))
//                .map(s -> "Value: " + s)
//                .sorted(String::compareTo)
//                .collect(Collectors.toList());
//    }
//
//    @Benchmark
//    public List<String> extractBooleanMethodRef(ArrayState state) {
//        return state.testList
//                .stream()
//                .filter(this::isaBoolean)
//                .filter(this::isaBoolean2)
//                .filter(this::isaBoolean3)
//                .filter(this::isaBoolean4)
//                .filter(this::isaBoolean5)
//                .filter(this::isaBoolean6)
//                .map(s -> "Value: " + s)
//                .sorted(String::compareTo)
//                .collect(Collectors.toList());
//    }

    private boolean isaBoolean6(String s) {
        return s.length() < 10;
    }

    private boolean isaBoolean5(String s) {
        return s.charAt(2) != 'b';
    }

    private boolean isaBoolean4(String s) {
        return s.charAt(1) != 'a';
    }

    private boolean isaBoolean3(String s) {
        return s.charAt(3) != 'c';
    }

    private boolean isaBoolean2(String s) {
        return s.length() > 5;
    }

    private boolean isaBoolean(String s) {
        return !s.isEmpty();
    }


    private static Predicate<String> getStringPredicate6() {
        return s -> s.length() < 10;
    }

    private static Predicate<String> getStringPredicate5() {
        return s -> s.charAt(2) != 'b';
    }

    private static Predicate<String> getStringPredicate4() {
        return s -> s.charAt(1) != 'a';
    }

    private static Predicate<String> getStringPredicate3() {
        return s -> s.charAt(3) != 'c';
    }

    private static Predicate<String> getStringPredicate2() {
        return s -> s.length() > 5;
    }

    private static Predicate<String> getStringPredicate() {
        return s -> !s.isEmpty();
    }


}

