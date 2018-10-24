package com.mfuhrmann.performance.validators;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@State(Scope.Thread)
@Warmup(iterations = 5)
public class ValidatorState {
    Validator validator;

    public ValidatorState() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
}
