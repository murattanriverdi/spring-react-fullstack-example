package com.murattanriverdi.app.exceptions;

import com.murattanriverdi.app.util.MessagesUtil;

public class ActivationNotificationException extends RuntimeException{

    public ActivationNotificationException(){
        super(MessagesUtil.getMessage("app.messages.error.activationNotification.error"));
    }
}
