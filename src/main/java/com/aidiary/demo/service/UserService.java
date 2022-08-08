package com.aidiary.demo.service;

import com.aidiary.demo.domain.User;
import com.aidiary.demo.dto.AccountRequestDto;
import com.aidiary.demo.exception.UserAuthException;
import com.aidiary.demo.repository.UserRepository;
import com.aidiary.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public String registerUser(AccountRequestDto requestDto) {
        String username = requestDto.getUserId();
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUserId(username);
        if (found.isPresent()) {
            throw new UserAuthException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 인코딩
        String password = passwordEncoder.encode(requestDto.getPassword());
        List<String> grades = Collections.singletonList("USER");
        User user = new User(username, password, grades);

        return userRepository.save(user).getUserId();
    }

    public String login(AccountRequestDto user)
    {
        User member = userRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new UserAuthException("가입되지 않은 아이디입니다."));
        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            throw new UserAuthException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getGrades());
    }

}