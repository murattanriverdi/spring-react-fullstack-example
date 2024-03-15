package com.murattanriverdi.app.error;

import com.murattanriverdi.app.auth.exception.AuthenticationException;
import com.murattanriverdi.app.common.ApiError;
import com.murattanriverdi.app.exceptions.ActivationNotificationException;
import com.murattanriverdi.app.exceptions.InvalidTokenException;
import com.murattanriverdi.app.exceptions.NonUniqueEmailException;
import com.murattanriverdi.app.exceptions.NotFoundException;
import com.murattanriverdi.app.util.MessagesUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ApiError apiError = ApiError
                .builder()
                .path(request.getRequestURI())
                .message(MessagesUtil.getMessage("app.message.error.validation.error"))
                .status(HttpStatus.BAD_REQUEST.value())
                .validationErrors(
                        exception
                                .getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing))
                ).build();
        return response(apiError);
    }

    @ExceptionHandler(NonUniqueEmailException.class)
    ResponseEntity<ApiError> handleNonUniqueMailException(NonUniqueEmailException exception, HttpServletRequest request) {
        ApiError apiError = getExceptionDetail(exception, request)
                .status(HttpStatus.BAD_REQUEST.value())
                .validationErrors(exception.getValidationErrors())
                .build();
        return response(apiError);
    }


    @ExceptionHandler(ActivationNotificationException.class)
    ResponseEntity<ApiError> handleActivationNotificationException(ActivationNotificationException exception, HttpServletRequest request) {
        ApiError apiError = getExceptionDetail(exception, request)
                .status(HttpStatus.BAD_GATEWAY.value())
                .build();
        return response(apiError);
    }


    @ExceptionHandler(InvalidTokenException.class)
    ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException exception, HttpServletRequest request) {
        ApiError apiError = getExceptionDetail(exception, request)
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return response(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiError> handleNotFoundException(NotFoundException exception, HttpServletRequest request) {
        ApiError apiError = getExceptionDetail(exception, request)
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return response(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException exception, HttpServletRequest request) {
        ApiError apiError = getExceptionDetail(exception, request)
                .status(HttpStatus.UNAUTHORIZED.value())
                .build();
        return response(apiError);
    }


    private ApiError.ApiErrorBuilder getExceptionDetail(Exception exception, HttpServletRequest request){
        return ApiError
                .builder()
                .path(request.getRequestURI())
                .message(exception.getMessage());
    }

    private ResponseEntity<ApiError> response(ApiError apiError){
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

}
