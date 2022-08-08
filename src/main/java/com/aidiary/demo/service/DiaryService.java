package com.aidiary.demo.service;

import com.aidiary.demo.domain.Diary;
import com.aidiary.demo.dto.DiaryResponseDto;
import com.aidiary.demo.dto.UpdateResponseDto;
import com.aidiary.demo.exception.DiaryException;
import com.aidiary.demo.repository.DiaryRepository;
import com.aidiary.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public List<UpdateResponseDto> getDiary(String userId)
    {
        List<UpdateResponseDto> diary = diaryRepository.findDiaryResponseDto(userId);
        if(diary.isEmpty())
        {
            throw new DiaryException("아직 없습니다");
        }
        return diary;
    }

    public UpdateResponseDto postDiary(DiaryResponseDto dto, String username){
        return diaryToUpdateDto(diaryRepository.save(Diary.builder()
                .userId(username)
                .content(dto.getContent())
                .date(new Date().getTime())
                .title(dto.getTitle())
                .build()));
    }

    public UpdateResponseDto updateDiary(Map<String, String> req) {
        int diaryId = Integer.parseInt(req.get("diaryId"));
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("아직 없습니다"));
        diary.changeRatings(req);
        return diaryToUpdateDto(diaryRepository.save(diary));
    }

    public DiaryResponseDto diaryToPostDto(Diary diary)
    {
        return new DiaryResponseDto(diary.getDiaryId(), diary.getUserId(), diary.getTitle(), diary.getContent(), diary.getRating1(), diary.getRating2(), diary.getRating3(),  diary.getDate());
    }

    public UpdateResponseDto diaryToUpdateDto(Diary diary)
    {
        return new UpdateResponseDto(diary.getDiaryId(), diary.getTitle(), diary.getContent(), diary.getRating1(), diary.getRating2(), diary.getRating3(),  diary.getDate());
    }

}
