package com.mfuhrmann.performance.validators;

import com.mfuhrmann.performance.DefaultBenchmark;
import org.hibernate.validator.constraints.NotEmpty;
import org.openjdk.jmh.annotations.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Fork(value = 1, warmups = 0)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 10)
public class ValidatorBenchmark extends DefaultBenchmark {

    @Benchmark
    public List<Set<ConstraintViolation<Car>>> _0_validate1Car(ValidatorState validatorState) {
        int iterations = 1;
        return validate(validatorState, iterations);
    }

    @Benchmark
    public List<Set<ConstraintViolation<Car>>> _1_validate10Car(ValidatorState validatorState) {
        int iterations = 10;
        return validate(validatorState, iterations);
    }

    @Benchmark
    public List<Set<ConstraintViolation<Car>>> _2_validate100Car(ValidatorState validatorState) {
        int iterations = 100;
        return validate(validatorState, iterations);
    }

    @Benchmark
    public List<Set<ConstraintViolation<Car>>> _3_validate1000Car(ValidatorState validatorState) {
        int iterations = 1000;
        return validate(validatorState, iterations);
    }

    @Benchmark
    public List<Set<ConstraintViolation<Car>>> _4_validate10_000Car(ValidatorState validatorState) {
        int iterations = 10_000;
        return validate(validatorState, iterations);
    }

    @Benchmark
    public List<Set<ConstraintViolation<Car>>> _5_validate100_000Car(ValidatorState validatorState) {
        int iterations = 100_000;
        return validate(validatorState, iterations);
    }


    private List<Set<ConstraintViolation<Car>>> validate(ValidatorState validatorState, int validations) {
        Validator validator = validatorState.validator;
        Car car = new Car.CarBuilder()
                .setOne("DD-AB-123")
                .setTwo(2L)
                .setThree(4.0)
                .setFour("qwe")
                .setFive(123L)
                .setSix(Instant.now().minusMillis(10))
                .setSeven("zxc")
                .setEight("qwe")
                .setNine("qwe")
                .setTen("qwe")
                .createCar();

        return IntStream.range(0, validations)
                .mapToObj(i -> validator.validate(car))
                .collect(Collectors.toList());
    }


    static class Car {
        @NotNull
        private String one;
        @NotNull
        private Long two;
        @NotNull
        @DecimalMin(value = "1.42")
        private Double three;
        @NotNull
        private String four;
        @NotNull
        @Min(3)
        private Long five;
        @NotNull
        @Past
        private Instant six;
        @NotEmpty
        private String seven;
        @NotEmpty
        private String eight;
        @NotEmpty
        private String nine;
        @NotEmpty
        private String ten;


        private Car(String one, Long two, Double three, String four, Long five, Instant six, String seven, String eight, String nine, String ten) {
            this.one = one;
            this.two = two;
            this.three = three;
            this.four = four;
            this.five = five;
            this.six = six;
            this.seven = seven;
            this.eight = eight;
            this.nine = nine;
            this.ten = ten;
        }

        CarBuilder getBuilder() {
            return new CarBuilder();
        }

        public static class CarBuilder {
            private String one;
            private Long two;
            private Double three;
            private String four;
            private Long five;
            private Instant six;
            private String seven;
            private String eight;
            private String nine;
            private String ten;

            public CarBuilder setOne(String one) {
                this.one = one;
                return this;
            }

            public CarBuilder setTwo(Long two) {
                this.two = two;
                return this;
            }

            public CarBuilder setThree(Double three) {
                this.three = three;
                return this;
            }

            public CarBuilder setFour(String four) {
                this.four = four;
                return this;
            }

            public CarBuilder setFive(Long five) {
                this.five = five;
                return this;
            }

            public CarBuilder setSix(Instant six) {
                this.six = six;
                return this;
            }

            public CarBuilder setSeven(String seven) {
                this.seven = seven;
                return this;
            }

            public CarBuilder setEight(String eight) {
                this.eight = eight;
                return this;
            }

            public CarBuilder setNine(String nine) {
                this.nine = nine;
                return this;
            }

            public CarBuilder setTen(String ten) {
                this.ten = ten;
                return this;
            }

            public Car createCar() {
                return new Car(one, two, three, four, five, six, seven, eight, nine, ten);
            }
        }
    }


}
