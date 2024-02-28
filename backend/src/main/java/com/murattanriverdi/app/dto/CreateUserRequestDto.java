package com.murattanriverdi.app.dto;

import com.murattanriverdi.app.custom_annotations.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequestDto(

        @NotBlank(message = "{app.constraint.user.validation.username.notBlank}")
        @Size(min = 4, max = 255, message = "{app.constraint.user.validation.username.size}")
        String username,

        @NotBlank(message = "{app.constraint.user.validation.email.notBlank}")
        @Email(message = "{app.constraint.user.validation.email.format}")
        @UniqueEmail
        String email,

        @Size(min = 8, max = 255, message = "{app.constraint.user.validation.password.size}")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{app.constraint.user.validation.password.pattern}")
        String password
) {
}
