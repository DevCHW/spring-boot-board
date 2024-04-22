package io.dodn.springboot.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UuidValidator implements ConstraintValidator<IsUUID, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("여기 오긴 옵니까?");
        return ValidationUtil.isUUID(value);
    }
}
