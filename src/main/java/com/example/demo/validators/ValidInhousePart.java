package com.example.demo.validators;

import javax.validation.Payload;

public @interface ValidInhousePart {
    String message() default "Error: Inventory couldn't be larger than the maximum or smaller than the minimum.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
