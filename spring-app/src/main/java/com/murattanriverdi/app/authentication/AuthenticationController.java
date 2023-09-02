package com.murattanriverdi.app.authentication;

import com.murattanriverdi.app.custom_annotations.CurrentUser;
import com.murattanriverdi.app.dto.response.UserResponseDto;
import com.murattanriverdi.app.entity.User;
import com.murattanriverdi.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final ModelMapper modelMapper;


    @PostMapping("/api/v1.0/auth")
    ResponseEntity<?> authenticate(@CurrentUser User user) {
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        return ResponseEntity.ok(userResponseDto);
    }

}
