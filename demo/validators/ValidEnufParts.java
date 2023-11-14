package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure that adding or updating a product
 * won't cause any associated part's inventory to fall below the minimum threshold.
 *
 * This annotation should be used on the Product entity class and is validated
 * using the EnufPartsValidator class.
 *
 * @see com.example.demo.validators.EnufPartsValidator
 */
@Constraint(validatedBy = {EnufPartsValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnufParts {
    String message() default "There aren't enough parts in inventory!";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};

}
