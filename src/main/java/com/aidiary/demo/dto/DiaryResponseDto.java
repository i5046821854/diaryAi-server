package com.aidiary.demo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DiaryResponseDto {

    private String userId;
    private String title;
    private String contents;
    private float rating1;
    private float rating2;
    private float rating3;
    private LocalDateTime date;

    public DiaryResponseDto(String userId, String title, String contents, float rating1, float rating2, float rating3, LocalDateTime date) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.date = date;
    }
}
