package com.murattanriverdi.app.auth.controller;


import com.murattanriverdi.app.auth.dao.AuthResponse;
import com.murattanriverdi.app.auth.dto.CredentialsDto;
import com.murattanriverdi.app.auth.exception.AuthenticationException;
import com.murattanriverdi.app.auth.service.IAuthService;
import com.murattanriverdi.app.common.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/auth")
    ResponseEntity<AuthResponse> handleAuthentication(@Valid @RequestBody CredentialsDto credentials) {
        return ResponseEntity.ok(authService.authenticate(credentials));
    }





}
