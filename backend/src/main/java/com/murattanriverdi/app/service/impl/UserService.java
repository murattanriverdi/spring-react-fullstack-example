package com.murattanriverdi.app.service.impl;

import com.murattanriverdi.app.dto.request.UserRequestDto;
import com.murattanriverdi.app.entity.User;
import com.murattanriverdi.app.exceptions.NonUniqueEmailException;
import com.murattanriverdi.app.mapper.UserMapper;
import com.murattanriverdi.app.repository.UserRepository;
import com.murattanriverdi.app.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void save(UserRequestDto userRequestDto) {
       try{
           User user = userMapper.requestDtoToUser(userRequestDto);
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           userRepository.save(user);
       }catch (DataIntegrityViolationException ex){
            throw new NonUniqueEmailException();
       }

    }
}
