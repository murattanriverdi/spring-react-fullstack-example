package com.murattanriverdi.app.exceptions;

import com.murattanriverdi.app.util.MessagesUtil;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(){
        super(MessagesUtil.getMessage("app.message.error.activationNotification.invalidToken"));
    }
}
