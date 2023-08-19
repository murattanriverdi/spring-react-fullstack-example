package com.murattanriverdi.app.customAnnotations.validator;

import com.murattanriverdi.app.customAnnotations.UniqueUsername;
import com.murattanriverdi.app.entity.User;
import com.murattanriverdi.app.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        User user = userRepository.findByUsername(username);
        if(null != user)
                return false;
        return true;
    }
}
