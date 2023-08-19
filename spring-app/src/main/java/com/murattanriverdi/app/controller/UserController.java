package com.murattanriverdi.app.controller;

import com.murattanriverdi.app.dto.UserDto;
import com.murattanriverdi.app.entity.User;
import com.murattanriverdi.app.error.ApiError;
import com.murattanriverdi.app.repository.UserRepository;
import com.murattanriverdi.app.service.IUserService;
import com.murattanriverdi.app.service.impl.UserService;
import com.murattanriverdi.app.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ApiResponse saveUser(@Valid @RequestBody UserDto userDto) {
        userService.save(userDto);
        return new ApiResponse("User created");

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidException(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError(400, "Validation error", "/api/v1.0/users");
        Map<String, String> validationErrors = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        apiError.setValidationErrors(validationErrors);
        return apiError;
    }


}
