package com.aidiary.demo.dto;

import lombok.Data;

@Data
public class AccountRequestDto {
    private String userId;
    private String password;
    private boolean admin = false;
}

