package com.murattanriverdi.app.auth.token.service.impl;

import com.murattanriverdi.app.auth.dto.CredentialsDto;
import com.murattanriverdi.app.auth.token.Token;
import com.murattanriverdi.app.auth.token.service.ITokenService;
import com.murattanriverdi.app.entity.User;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class BasicAuthTokenService implements ITokenService {
    @Override
    public Token createToken(User user, CredentialsDto credentials) {
        String emailPassword = credentials.email().concat(":").concat(credentials.password());
        String token = Base64.getEncoder().encodeToString(emailPassword.getBytes());
        return new Token("Basic", token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }
}
