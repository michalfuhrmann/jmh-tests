package com.mfuhrmann.performance.stream;

class TestAllocation {
    static final int CHUNK_SIZE = 1000;

    public static void main(String[] args) {

        for (int i = 0; i < 500; i++) {
            long startTime = System.nanoTime();
            for (int j = 0; j < CHUNK_SIZE; j++) {
                new Object();
            }
            long endTime = System.nanoTime();
            System.out.printf("%d\t%d%n", i, endTime - startTime);
        }

    }
}