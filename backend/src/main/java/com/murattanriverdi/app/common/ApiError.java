package com.murattanriverdi.app.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
public class ApiError {

    private int status;

    private String message;

    private String path;

    private long timestamp = new Date().getTime();

    private Map<String,String> validationErrors = null;
}
