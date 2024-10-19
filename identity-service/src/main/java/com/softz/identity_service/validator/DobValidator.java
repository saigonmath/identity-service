package com.softz.identity_service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintViolation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DobValidator implements ConstraintValidator<DobConstraint, LocalDate> {
    private long min;
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

        long age = ChronoUnit.YEARS.between(value, LocalDate.now());

        return age >= min;
    }

    @Override
    public void initialize(DobConstraint constraintAnnotation) {
        min = constraintAnnotation.min();
    }
}
