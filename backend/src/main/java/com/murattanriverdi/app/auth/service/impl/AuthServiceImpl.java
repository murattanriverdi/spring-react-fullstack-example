package com.murattanriverdi.app.auth.service.impl;

import com.murattanriverdi.app.auth.dao.AuthResponse;
import com.murattanriverdi.app.auth.dto.CredentialsDto;
import com.murattanriverdi.app.auth.exception.AuthenticationException;
import com.murattanriverdi.app.auth.service.IAuthService;
import com.murattanriverdi.app.auth.token.service.ITokenService;
import com.murattanriverdi.app.entity.User;
import com.murattanriverdi.app.mapper.UserMapper;
import com.murattanriverdi.app.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {


    private final IUserService userService;
    private final ITokenService tokenService;
    private final UserMapper userMapper;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public AuthResponse authenticate(CredentialsDto credentials) {
        User user = checkUserCredentials(credentials);

        return AuthResponse
                .builder()
                .token(tokenService.createToken(user,credentials))
                .user(userMapper.userToUserListDao(user))
                .build();
    }

    /**
     * check user email and password is valid
     * @param credentials
     */
    private User checkUserCredentials(CredentialsDto credentials){
        User user = userService.findByEmail(credentials.email()).orElseThrow(AuthenticationException::new);
        if(!passwordEncoder.matches(credentials.password(), user.getPassword())){
            throw new AuthenticationException();
        }
        return user;
    }
}
