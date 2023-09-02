package com.murattanriverdi.app.dto.request;

import com.murattanriverdi.app.custom_annotations.UniqueUsername;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotNull(message = "{app.constraint.username.NotNull.message}")
    @Size(min = 4, max = 255, message = "{app.constraint.username.Size.message}" )
    @UniqueUsername
    private String username;

    @NotNull(message = "{app.constraint.name.NotNull.message}")
    @Size(min = 4, max = 255, message = "{app.constraint.name.Size.message}" )
    private String name;

    @NotNull(message = "{app.constraint.password.NotNull.message}")
    @Size(min = 8, max = 255, message = "{app.constraint.password.Size.message}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{app.constraint.password.Pattern.message}")
    private String password;

    private String image;

}
