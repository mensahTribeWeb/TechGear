package com.example.demo.validators;

import com.example.demo.domain.OutsourcedPart;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidOutsourcedPartValidator implements ConstraintValidator<ValidOutsourcedPart, OutsourcedPart> {
    @Override
    public void initialize(ValidOutsourcedPart constraintAnnotation) {
    }

    @Override
    public boolean isValid(OutsourcedPart outsourcedPart, ConstraintValidatorContext context) {
        if (outsourcedPart == null) {
            return true; // Null values will be handled by other validation annotations.
        }

        int minInv = outsourcedPart.getMinInv();
        int maxInv = outsourcedPart.getMaxInv();
        int inv = outsourcedPart.getInv();

        // Perform your validation logic here
        return inv >= minInv && inv <= maxInv;
    }
}