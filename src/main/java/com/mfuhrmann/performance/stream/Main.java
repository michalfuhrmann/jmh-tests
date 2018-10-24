package com.mfuhrmann.performance.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {






    public static void main(String[] args) {


        Object o = new Object();

        List<Object> objects = Arrays.asList(o, o);

        System.out.println(objects.size());


        System.out.println(objects.stream().count());
        System.out.println(objects.stream().distinct().count());

    }

    static class Wrapper{

        private int value;

        public Wrapper(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Wrapper{" +
                    "value=" + value +
                    '}';
        }
    }
}
