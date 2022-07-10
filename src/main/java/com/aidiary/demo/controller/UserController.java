package com.aidiary.demo.controller;

import com.aidiary.demo.domain.User;
import com.aidiary.demo.dto.AccountRequestDto;
import com.aidiary.demo.repository.UserRepository;
import com.aidiary.demo.security.JwtTokenProvider;
import com.aidiary.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    public String join(@RequestBody AccountRequestDto user) {
        return userService.registerUser(user);
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody AccountRequestDto user) {
        return userService.login(user);
    }

}
