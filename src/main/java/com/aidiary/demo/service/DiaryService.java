package com.aidiary.demo.service;

import com.aidiary.demo.domain.Diary;
import com.aidiary.demo.domain.User;
import com.aidiary.demo.dto.DiaryResponseDto;
import com.aidiary.demo.exception.DiaryException;
import com.aidiary.demo.repository.DiaryRepository;
import com.aidiary.demo.repository.UserRepository;
import com.aidiary.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public List<DiaryResponseDto> getDiary(String userId)
    {
        List<DiaryResponseDto> diary = diaryRepository.findDiaryResponseDto(userId);
        if(diary.isEmpty())
        {
            throw new DiaryException("아직 없습니다");
        }
        return diary;
    }

    public DiaryResponseDto postDiary(DiaryResponseDto dto, String username){
        return diaryToDto(diaryRepository.save(Diary.builder()
                .userId(username)
                .contents(dto.getContents())
                .date(LocalDateTime.now())
                .title(dto.getTitle())
                .rating1(dto.getRating1())
                .rating2(dto.getRating2())
                .rating3(dto.getRating3())
                .build()));
    }

    public DiaryResponseDto updateDiary(Map<String, String> req) {
        int diaryId = Integer.parseInt(req.get("diaryId"));
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new IllegalArgumentException("아직 없습니다"));
        diary.changeRatings(req);
        return diaryToDto(diaryRepository.save(diary));
    }

    public DiaryResponseDto diaryToDto(Diary diary)
    {
        return new DiaryResponseDto(diary.getDiaryId(), diary.getUserId(), diary.getTitle(), diary.getContents(), diary.getRating1(), diary.getRating2(), diary.getRating3(),  diary.getDate());
    }
}
