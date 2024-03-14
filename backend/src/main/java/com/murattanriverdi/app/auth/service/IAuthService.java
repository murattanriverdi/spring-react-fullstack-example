package com.murattanriverdi.app.auth.service;

import com.murattanriverdi.app.auth.dao.AuthResponse;
import com.murattanriverdi.app.auth.dto.CredentialsDto;

public interface IAuthService {

    AuthResponse authenticate(CredentialsDto credentials);
}
