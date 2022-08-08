package com.aidiary.demo.controller;

import com.aidiary.demo.domain.User;
import com.aidiary.demo.dto.AccountRequestDto;
import com.aidiary.demo.exception.UserAuthException;
import com.aidiary.demo.repository.UserRepository;
import com.aidiary.demo.security.JwtTokenProvider;
import com.aidiary.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 예외 핸들러
    @ExceptionHandler(value = UserAuthException.class)
    public Map<String, String> controllerExceptionHandler(Exception e) {
        Map<String, String> result = new HashMap<>();
        result.put("error", e.getMessage());
        return result;
    }


    // 회원가입
    @PostMapping("/join")
    public Map<String, String> join(@RequestBody AccountRequestDto user) {
        String userId = userService.registerUser(user);
        Map<String, String> result = new HashMap<>();
        result.put("userId", userId);
        return result;
    }

    // 로그인
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AccountRequestDto user) {
        String token = userService.login(user);
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        return result;
    }

    @GetMapping("/user")
    public String user(@RequestBody AccountRequestDto user) {
        return "user";
    }

}
