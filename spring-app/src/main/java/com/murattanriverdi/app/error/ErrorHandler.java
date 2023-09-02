package com.murattanriverdi.app.error;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class ErrorHandler  implements ErrorController {

    private final ErrorAttributes errorAttributes;


    @RequestMapping("/error")
    public ApiError handleError(WebRequest webRequest){
        Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.BINDING_ERRORS));

        String message = (String) attributes.get("message");
        String path = (String) attributes.get("path");
        int status = (Integer) attributes.get("status");
        ApiError error =  new ApiError(status, message, path);


        // validation exceptions handler
        if (attributes.containsKey("errors")) {
            @SuppressWarnings("unchecked")
            List<FieldError> fieldErrors = (List<FieldError>) attributes.get("errors");
            Map<String, String> validationErrors = new HashMap<>();
           Optional
                   .ofNullable(fieldErrors)
                   .orElse(Collections.emptyList())
                   .forEach(fieldError -> validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            error.setValidationErrors(validationErrors);
        }

        return error;



    }



}
