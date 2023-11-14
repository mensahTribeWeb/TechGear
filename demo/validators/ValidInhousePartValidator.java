package com.example.demo.validators;

import com.example.demo.domain.InhousePart;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidInhousePartValidator implements ConstraintValidator<ValidInhousePart, InhousePart> {
    @Override
    public void initialize(ValidInhousePart constraintAnnotation) {
    }

    @Override
    public boolean isValid(InhousePart inhousePart, ConstraintValidatorContext context) {
        if (inhousePart == null) {
            return true; // Null values will be handled by other validation annotations.
        }

        Integer minInv = inhousePart.getMinInv();
        Integer maxInv = inhousePart.getMaxInv();

        if (minInv == null || maxInv == null) {
            return false; // Handle the case when minInv or maxInv is null.
        }

        int inv = inhousePart.getInv();

        // Perform your validation logic here
        return inv >= minInv && inv <= maxInv;
    }
}
