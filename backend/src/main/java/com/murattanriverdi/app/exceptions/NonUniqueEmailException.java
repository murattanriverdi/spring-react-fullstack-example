package com.murattanriverdi.app.exceptions;

import com.murattanriverdi.app.util.MessagesUtil;

import java.util.Collections;
import java.util.Map;

public class NonUniqueEmailException extends RuntimeException {

    public NonUniqueEmailException(){
        super(MessagesUtil.getMessage("app.messages.error.validation.error"));
    }

    public Map<String,String> getValidationErrors(){
        return Collections
                .singletonMap("email",MessagesUtil.getMessage("app.message.user.create.uniqueEmail"));
    }
}
