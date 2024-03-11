package com.murattanriverdi.app.exceptions;

import com.murattanriverdi.app.util.MessagesUtil;

public class NotFoundException extends  RuntimeException {

    public NotFoundException(Long id){
        super(MessagesUtil.getMessagesWithArguments("app.message.user.not.found", id.toString()));
    }
}
