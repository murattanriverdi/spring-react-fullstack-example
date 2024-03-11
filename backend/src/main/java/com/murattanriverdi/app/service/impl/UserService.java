package com.murattanriverdi.app.service.impl;

import com.murattanriverdi.app.dao.UserListDao;
import com.murattanriverdi.app.dto.CreateUserRequestDto;
import com.murattanriverdi.app.email.IEmailService;
import com.murattanriverdi.app.entity.User;
import com.murattanriverdi.app.exceptions.ActivationNotificationException;
import com.murattanriverdi.app.exceptions.InvalidTokenException;
import com.murattanriverdi.app.exceptions.NonUniqueEmailException;
import com.murattanriverdi.app.exceptions.NotFoundException;
import com.murattanriverdi.app.mapper.UserMapper;
import com.murattanriverdi.app.repository.UserRepository;
import com.murattanriverdi.app.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    public void save(CreateUserRequestDto createUserRequestDto) {
        try {
            User user = userMapper.requestDtoToUser(createUserRequestDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.saveAndFlush(user); //bu anda flush edip db ye yazmaya çalışması için.
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
        } catch (DataIntegrityViolationException ex) {
            throw new NonUniqueEmailException();
        } catch (MailException ex) {
            throw new ActivationNotificationException();
        }
    }

    @Override
    public void activateUser(String token) {
        Optional<User> userOpt = userRepository.findByActivationToken(token);
        if (userOpt.isEmpty()) {
            throw new InvalidTokenException();
        }
        User user = userOpt.get();

        user.setActive(Boolean.TRUE);
        user.setActivationToken(null);
        userRepository.saveAndFlush(user);

    }

    @Override
    public Page<UserListDao> getUserList(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::userToUserListDao);
    }

    @Override
    public UserListDao getUserById(Long id) {
        return userMapper.userToUserListDao(userRepository.findById(id).orElseThrow(() -> new NotFoundException(id)));
    }

}
