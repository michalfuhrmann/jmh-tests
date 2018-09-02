package com.mfuhrmann.performance.trade;


import com.mfuhrmann.performance.DefaultBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;

import java.math.BigDecimal;

@Fork(value = 1, warmups = 0)
public class TradeReadBenchmark extends DefaultBenchmark {

    @Benchmark
    public BigDecimal testStream(TradeState state) {
        return state.getTrades()
                .stream()
                .filter(trade -> trade.getCurrency().equals(TradeState.Currency.PLN))
                .map(TradeState.Trade::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    @Benchmark
    public BigDecimal testParallelStream(TradeState state) {
        return state.getTrades().stream().parallel()
                .filter(trade -> trade.getCurrency().equals(TradeState.Currency.PLN))
                .map(TradeState.Trade::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }



    @Benchmark
    public int testSum(TradeState state) {
        return state.getTrades().stream()
                .mapToInt(TradeState.Trade::getIndex)
                .sum();
    }

}
