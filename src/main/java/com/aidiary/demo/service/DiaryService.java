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

import java.io.*;
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
    private final AsyncTaskService asyncTaskService;

    public List<UpdateResponseDto> getDiary(String userId)
    {
        List<UpdateResponseDto> diary = diaryRepository.findDiaryResponseDto(userId);
        if(diary.isEmpty())
        {
            throw new DiaryException("아직 없습니다");
        }
        return diary;
    }

//    public UpdateResponseDto postDiary(DiaryResponseDto dto, String username){
//        return diaryToUpdateDto(diaryRepository.save(Diary.builder()
//                .userId(username)
//                .content(dto.getContent())
//                .date(new Date().getTime())
//                .title(dto.getTitle())
//                .build()));
//    }

    public UpdateResponseDto postDiary(String content, String username) throws IOException, InterruptedException {
        UpdateResponseDto dto = diaryToUpdateDto(diaryRepository.save(Diary.builder()
        .userId(username)
        .content(content)
        .date(new Date().getTime())
        .title("temporary title").status(1)
        .build()));
        asyncTaskService.jobRunningInBackground(dto);
//        String id = dto.getDiary_id().toString();
////        String fileName = "diary_" + id + ".txt";
//        String fileName = "C:/Users/o5046/Documents/KakaoTalkChats.txt";
////        try {
////            String name = "C:/Users/o5046/Documents/"+ fileName;
////            OutputStream output = new FileOutputStream(name);
////            String str =content;
////            byte[] by=str.getBytes();
////            output.write(by);
////        } catch (Exception e) {
////            e.getStackTrace();
////        }
//        ProcessBuilder builder;
//        String arg1 = "C:/Users/o5046/Documents/keyword.py";
//        builder = new ProcessBuilder("python",arg1, fileName, id);
//        builder.redirectErrorStream(true);
//        Process process = builder.start();
//        System.out.println("process start");
//        // 자식 프로세스가 종료될 때까지 기다림
//        int exitVal = process.waitFor();
//        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr")); // 서브 프로세스가 출력하는 내용을 받기 위해
//        String line;
//        while ((line = br.readLine()) != null) {
//            System.out.println(">>>  " + line); // 표준출력에 쓴다
//        }
//        if(exitVal != 0) {
//            // 비정상 종료
//            System.out.println("서브 프로세스가 비정상 종료되었다.");
//        }
//        System.out.println("process terminated");
//
        return dto;

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
