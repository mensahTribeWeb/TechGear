package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure that the price of a product is greater than or equal
 * to the sum of the prices of its associated parts.
 *
 * This annotation should be used on the Product entity class and is validated
 * using the PriceProductValidator class.
 *
 * @see com.example.demo.validators.PriceProductValidator
 */
@Constraint(validatedBy = {PriceProductValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProductPrice {
    String message() default "Price of the product must be greater than the sum of the price of the parts.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
