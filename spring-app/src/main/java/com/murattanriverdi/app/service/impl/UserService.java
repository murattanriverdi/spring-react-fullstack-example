package com.murattanriverdi.app.service.impl;

import com.murattanriverdi.app.dto.UserDto;
import com.murattanriverdi.app.entity.User;
import com.murattanriverdi.app.repository.UserRepository;
import com.murattanriverdi.app.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(ModelMapper modelMapper, UserRepository userRepository){
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
