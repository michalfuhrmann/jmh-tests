package com.mfuhrmann.performance.trade;


import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(Scope.Thread)
@Warmup(iterations = 5)
public class TradeState {

    public static final int TRADE_SIZE = 1_000_000;
    public static final int ORDERS_SIZE = 1;
    private final List<Trade> trades;

    public TradeState() {
        this.trades = IntStream.range(0, TRADE_SIZE).mapToObj(i ->
                new Trade(BigDecimal.valueOf(i % 10_000),
                        "from" + i % 100,
                        "to" + i % 30,
                        Currency.values()[i % Currency.values().length],
                        createOrders(),
                        createOrders()))
                .collect(Collectors.toList());
    }

    private List<BookOrder> createOrders() {
        return IntStream.range(0, ORDERS_SIZE)
                .mapToObj(i -> new BookOrder(LocalDateTime.now().minusSeconds(i), BigDecimal.valueOf(i & 10)))
                .collect(Collectors.toList());
    }

    public List<Trade> getTrades() {
        return trades;
    }

    static class Trade {

        private final BigDecimal value;
        private final String from;
        private final String to;
        private final int index = 20;
        private final Currency currency;
        private final List<BookOrder> buys;
        private final List<BookOrder> sells;

        Trade(BigDecimal value, String from, String to, Currency currency, List<BookOrder> buys, List<BookOrder> sells) {
            this.value = value;
            this.from = from;
            this.to = to;
            this.currency = currency;
            this.buys = buys;
            this.sells = sells;
        }

        public BigDecimal getValue() {
            return value;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public Currency getCurrency() {
            return currency;
        }

        public List<BookOrder> getBuys() {
            return buys;
        }

        public List<BookOrder> getSells() {
            return sells;
        }

        public int getIndex() {
            return index;
        }
    }


    static class BookOrder {

        private final LocalDateTime timestamp;
        private final BigDecimal orderValue;

        BookOrder(LocalDateTime timestamp, BigDecimal orderValue) {
            this.timestamp = timestamp;
            this.orderValue = orderValue;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public BigDecimal getOrderValue() {
            return orderValue;
        }
    }

    enum Currency {
        EUR, USD, PLN, CHF, GBP, JPY, TRX,
    }

}
