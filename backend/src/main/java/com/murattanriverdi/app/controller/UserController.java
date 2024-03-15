package com.murattanriverdi.app.controller;

import com.murattanriverdi.app.dao.UserDao;
import com.murattanriverdi.app.dto.CreateUserRequestDto;
import com.murattanriverdi.app.service.IUserService;
import com.murattanriverdi.app.common.ApiResponse;
import com.murattanriverdi.app.util.MessagesUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping(path = "/create")
    ResponseEntity<ApiResponse> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        userService.save(createUserRequestDto);
        return ResponseEntity.ok(new ApiResponse(MessagesUtil.getMessage("app.message.user.create.success")));
    }

    @PatchMapping("/{token}/active")
    ResponseEntity<ApiResponse> activeUser(@PathVariable String token) {
        userService.activateUser(token);
        return ResponseEntity.ok(new ApiResponse(MessagesUtil.getMessage("app.message.user.activate.success")));
    }

    @GetMapping("/list")
    Page<UserDao> getUserList(Pageable pageable) {
        return userService.getUserList(pageable);
    }

    @GetMapping("/get/{id}")
    UserDao getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }




}

