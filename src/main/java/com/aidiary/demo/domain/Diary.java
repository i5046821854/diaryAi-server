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
    private String contents;

    private float rating1;

    private float rating2;

    private float rating3;

    private LocalDateTime date;

    public Diary(Integer id, String userId, String title, String contents, float rating1, float rating2, float rating3, LocalDateTime date) {
        this.diaryId = id;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.date = date;
    }

    @Builder
    public Diary(String userId, String title, String contents, float rating1, float rating2, float rating3, LocalDateTime date) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.date = date;
    }

    public void changeRatings(Map<String, String> newRatings)
    {
        this.rating1 = Float.parseFloat(newRatings.get("rating1"));
        this.rating2 = Float.parseFloat(newRatings.get("rating2"));
        this.rating3 = Float.parseFloat(newRatings.get("rating3"));

    }
}
