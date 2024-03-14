package com.murattanriverdi.app.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CredentialsDto(
        @Email
        String email,
        @NotNull
        String password
){

}