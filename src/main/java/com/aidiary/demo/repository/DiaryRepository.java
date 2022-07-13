package com.aidiary.demo.repository;

import com.aidiary.demo.domain.Diary;
import com.aidiary.demo.domain.User;
import com.aidiary.demo.dto.DiaryResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    @Query("select new com.aidiary.demo.dto.DiaryResponseDto(m.diaryId, m.userId, m.title, m.content, m.rating1, m.rating2, m.rating3, m.date) from Diary m where m.userId = :userId")
    List<DiaryResponseDto> findDiaryResponseDto(@Param("userId") String userId);

    Optional<Diary> findById(int id);

}
