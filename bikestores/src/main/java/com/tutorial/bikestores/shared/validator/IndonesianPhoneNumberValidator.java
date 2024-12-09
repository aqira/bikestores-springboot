package com.tutorial.bikestores.shared.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class IndonesianPhoneNumberValidator implements ConstraintValidator<IndonesianPhoneNumber, String> {
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (!phoneNumber.startsWith("+62")){
            return false;
        }
        return true;
    }
}
