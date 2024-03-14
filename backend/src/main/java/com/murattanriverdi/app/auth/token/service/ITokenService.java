package com.murattanriverdi.app.auth.token.service;

import com.murattanriverdi.app.auth.dto.CredentialsDto;
import com.murattanriverdi.app.auth.token.Token;
import com.murattanriverdi.app.entity.User;

public interface ITokenService {

    public Token createToken(User user, CredentialsDto credentials);
    public User verifyToken(String authorizationHeader);
}
