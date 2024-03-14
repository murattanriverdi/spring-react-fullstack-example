package com.murattanriverdi.app.service;


import com.murattanriverdi.app.dao.UserDao;
import com.murattanriverdi.app.dto.CreateUserRequestDto;
import com.murattanriverdi.app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IUserService {

    void save(CreateUserRequestDto userRequestDto);

     void activateUser(String token);

    Page<UserDao> getUserList(Pageable pageable);

    UserDao getUserById(Long id);

    Optional<User> findByEmail(String email);
}
