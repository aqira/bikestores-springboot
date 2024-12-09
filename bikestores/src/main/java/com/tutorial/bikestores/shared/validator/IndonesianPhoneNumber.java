package com.tutorial.bikestores.shared.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.context.annotation.PropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IndonesianPhoneNumberValidator.class)
public @interface IndonesianPhoneNumber {

    String message() default "The number needs to have a prefix of +62";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
