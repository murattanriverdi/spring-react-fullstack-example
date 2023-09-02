package com.murattanriverdi.app.custom_annotations.validator;

import com.murattanriverdi.app.custom_annotations.UniqueUsername;
import com.murattanriverdi.app.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return null == userRepository.findByUsername(username);
    }
}
