package com.murattanriverdi.app.controller;

import com.murattanriverdi.app.dto.request.UserRequestDto;
import com.murattanriverdi.app.error.ApiError;
import com.murattanriverdi.app.service.IUserService;
import com.murattanriverdi.app.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@Log
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/api/v1.0/users")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse saveUser(@Valid @RequestBody UserRequestDto userDto) {
        userService.save(userDto);
        return new ApiResponse("User created");

    }



}
