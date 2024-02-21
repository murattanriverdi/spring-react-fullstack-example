package com.murattanriverdi.app.email;


public interface IEmailService {

    public void sendActivationEmail(String email, String activationToken);
}
