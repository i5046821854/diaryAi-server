package com.aidiary.demo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Entity
@Data
@Table(name = "diary")
@NoArgsConstructor
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer diaryId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    private float rating1 = 0;

    private float rating2 = 0;

    private float rating3 = 0;

    private Long date;

    private int status;

    public Diary(Integer id, String userId, String title, String content, Long date, int status) {
        this.diaryId = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
    }

    @Builder
    public Diary(String userId, String title, String content, Long date, int status) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
    }

    public void changeRatings(Map<String, String> newRatings)
    {
        this.rating1 = Float.parseFloat(newRatings.get("rating1"));
        this.rating2 = Float.parseFloat(newRatings.get("rating2"));
        this.rating3 = Float.parseFloat(newRatings.get("rating3"));
    }
}
