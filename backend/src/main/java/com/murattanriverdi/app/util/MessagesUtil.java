package com.murattanriverdi.app.util;

import org.springframework.context.i18n.LocaleContextHolder;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesUtil {

    private MessagesUtil(){}

    public static String getMessageForLocale(String messageKey, Locale locale){
        return ResourceBundle.getBundle("messages",locale).getString(messageKey);
    }

    public static String getMessage(String messageKey){
        return getMessageForLocale(messageKey, LocaleContextHolder.getLocale());
    }

    public static String getMessagesWithArguments(String messageKey, Object... arguments){
        return MessageFormat.format(getMessage(messageKey), arguments);
    }
}
