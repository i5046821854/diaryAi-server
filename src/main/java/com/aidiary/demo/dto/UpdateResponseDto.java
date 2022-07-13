package com.aidiary.demo.dto;

import com.aidiary.demo.dto.DiaryResponseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class UpdateResponseDto {

    private Integer diary_id;
    private String title;
    private String content;
    private float rating1;
    private float rating2;
    private float rating3;
    private Long date;

    public UpdateResponseDto(Integer diaryId, String title, String contents, float rating1, float rating2, float rating3, Long date) {
        this.diary_id = diaryId;
        this.title = title;
        this.content = contents;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.date = date;
    }

}
