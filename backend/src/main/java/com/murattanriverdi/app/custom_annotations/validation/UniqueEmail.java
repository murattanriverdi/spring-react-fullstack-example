package com.murattanriverdi.app.custom_annotations.validation;

import com.murattanriverdi.app.custom_annotations.validation.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)

public @interface UniqueEmail {

    String message() default "{app.constraint.user.validation.email.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
