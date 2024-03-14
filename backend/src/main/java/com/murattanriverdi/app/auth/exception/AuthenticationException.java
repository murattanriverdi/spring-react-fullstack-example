package com.murattanriverdi.app.auth.exception;

import com.murattanriverdi.app.util.MessagesUtil;

public class AuthenticationException extends  RuntimeException{

    public  AuthenticationException(){
        super(MessagesUtil.getMessage("app.message.auth.invalid.credentials"));
    }
}
