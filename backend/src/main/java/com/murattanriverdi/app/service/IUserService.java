package com.murattanriverdi.app.service;


import com.murattanriverdi.app.dao.UserListDao;
import com.murattanriverdi.app.dto.CreateUserRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IUserService {

    public void save(CreateUserRequestDto userRequestDto);

    public void activateUser(String token);

    Page<UserListDao> getUserList(Pageable pageable);
}
