package com.murattanriverdi.app.custom_annotations.validation.validator;

import com.murattanriverdi.app.custom_annotations.validation.UniqueEmail;
import com.murattanriverdi.app.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findUserByEmail(s).isEmpty();
    }
}
