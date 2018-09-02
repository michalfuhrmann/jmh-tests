package com.mfuhrmann.performance;

import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Warmup;

@Warmup(iterations = 5)
@Fork(value = 1, warmups = 0)
public class DefaultBenchmark {
}
