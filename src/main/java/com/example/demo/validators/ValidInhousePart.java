package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {ValidInhousePartValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInhousePart {
    String message() default "Error: Inventory cannot be larger than the maximum or smaller than the minimum.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
