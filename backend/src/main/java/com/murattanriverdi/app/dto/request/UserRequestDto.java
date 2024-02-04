package com.murattanriverdi.app.dto.request;

import com.murattanriverdi.app.custom_annotations.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "{app.constraint.user.validation.username.notBlank}")
    @Size(min = 4, max = 255 , message = "{app.constraint.user.validation.username.size}")
    private String username;

    @NotBlank(message = "{app.constraint.user.validation.email.notBlank}")
    @Email(message = "{app.constraint.user.validation.email.format}")
    @UniqueEmail
    private String email;

    @Size(min = 8, max = 255, message = "{app.constraint.user.validation.password.size}")
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$" ,message = "{app.constraint.user.validation.password.pattern}")
    private String password;

}
