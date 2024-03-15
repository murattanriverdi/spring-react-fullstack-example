package com.murattanriverdi.app.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CredentialsDto(
        @Email
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String password
){

}