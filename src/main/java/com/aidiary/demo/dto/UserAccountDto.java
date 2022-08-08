package com.aidiary.demo.dto;

import com.aidiary.demo.domain.User;

import java.util.List;

public record UserAccountDto(String userId,
                             String password,
                             List<String> grades
) {

    public static UserAccountDto of(String userID, String password, List<String> roleType)
    {
        return new UserAccountDto(userID, password, roleType);
    }

    public static UserAccountDto from(User entity)
    {
        return new UserAccountDto(entity.getUserId(), entity.getPassword(), entity.getGrades());
    }

}
