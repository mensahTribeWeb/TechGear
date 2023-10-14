package com.example.demo.validators;

import javax.validation.Payload;

/**
 * Custom validation annotation to indicate that a part cannot be deleted if it is used in a product.
 *
 * <p>
 * When applied to a field or method parameter, this annotation enforces the constraint that a part cannot be deleted
 * if it is associated with one or more products. If the constraint is violated, a validation error will be raised.
 * </p>
 *
 * <p>
 * This annotation can be used in conjunction with the Bean Validation framework to perform validation checks on
 * the associated fields or parameters.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>
 * {@code
 * public class MyEntity {
 *     @ValidDeletePart
 *     private Part part;
 *     // ...
 * }
 * }
 * </pre>
 *
 * @see javax.validation.Constraint
 * @see javax.validation.Payload
 */
public @interface ValidDeletePart {
    String message() default "Part cannot be deleted if used in a product.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
