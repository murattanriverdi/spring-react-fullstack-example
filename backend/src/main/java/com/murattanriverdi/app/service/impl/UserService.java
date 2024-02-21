package com.murattanriverdi.app.service.impl;

import com.murattanriverdi.app.dto.UserRequestDto;
import com.murattanriverdi.app.email.IEmailService;
import com.murattanriverdi.app.entity.User;
import com.murattanriverdi.app.exceptions.ActivationNotificationException;
import com.murattanriverdi.app.exceptions.NonUniqueEmailException;
import com.murattanriverdi.app.mapper.UserMapper;
import com.murattanriverdi.app.repository.UserRepository;
import com.murattanriverdi.app.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final IEmailService emailService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    @Transactional(rollbackOn = MailException.class)
    public void save(UserRequestDto userRequestDto) {
        try {
            User user = userMapper.requestDtoToUser(userRequestDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.saveAndFlush(user); //bu anda flush edip db ye yazmaya çalışması için.
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
        } catch (DataIntegrityViolationException ex) {
            throw new NonUniqueEmailException();
        }catch (MailException ex){
            throw new ActivationNotificationException();
        }
    }

}
