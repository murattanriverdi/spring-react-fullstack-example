package com.murattanriverdi.app.controller;

import com.murattanriverdi.app.common.ApiError;
import com.murattanriverdi.app.dao.UserListDao;
import com.murattanriverdi.app.dto.CreateUserRequestDto;
import com.murattanriverdi.app.exceptions.ActivationNotificationException;
import com.murattanriverdi.app.exceptions.InvalidTokenException;
import com.murattanriverdi.app.exceptions.NonUniqueEmailException;
import com.murattanriverdi.app.service.IUserService;
import com.murattanriverdi.app.common.ApiResponse;
import com.murattanriverdi.app.util.MessagesUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping(path = "/create")
    ResponseEntity<ApiResponse> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        userService.save(createUserRequestDto);
        return ResponseEntity.ok(new ApiResponse(MessagesUtil.getMessage("app.message.user.create.success")));
    }

    @PatchMapping("/{token}/active")
    ResponseEntity<ApiResponse> activeUser(@PathVariable String token){
        userService.activateUser(token);
        return ResponseEntity.ok(new ApiResponse(MessagesUtil.getMessage("app.message.user.activate.success")));
    }

    @GetMapping("/list")
    Page<UserListDao> getUserList(Pageable pageable){
        return userService.getUserList(pageable);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users/create");
        apiError.setMessage(MessagesUtil.getMessage("app.messages.error.validation.error"));
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());

        apiError.setValidationErrors(
                exception
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,(existing, replacing)->existing))
        );
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(NonUniqueEmailException.class)
    ResponseEntity<ApiError> handleNonUniqueMailException(NonUniqueEmailException exception){
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users/create");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        apiError.setValidationErrors(exception.getValidationErrors());
        return ResponseEntity.badRequest().body(apiError);

    }


    @ExceptionHandler(ActivationNotificationException.class)
    ResponseEntity<ApiError> handleActivationNotificationException(ActivationNotificationException exception){
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users/create");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(HttpStatus.BAD_GATEWAY.value());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(apiError);

    }


    @ExceptionHandler(InvalidTokenException.class)
    ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException exception){
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users/create");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

    }

}

