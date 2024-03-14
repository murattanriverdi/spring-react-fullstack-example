package com.murattanriverdi.app.auth.dao;

import com.murattanriverdi.app.auth.token.Token;
import com.murattanriverdi.app.dao.UserDao;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {

    UserDao user;
    Token token;

}
