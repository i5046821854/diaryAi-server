package com.aidiary.demo.controller;

import com.aidiary.demo.dto.DiaryResponseDto;
import com.aidiary.demo.service.DiaryService;
import com.aidiary.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DiaryController {


    private final DiaryService diaryService;

/*    @GetMapping("/getDiary")
    public List<DiaryResponseDto> getDiaryTest(@RequestParam Map<String, String> req, @RequestAttribute("username") String username)
    {
        String userId = req.get("userId");
        return diaryService.getDiary(userId);
    }*/

    @GetMapping("/getDiary")
    public List<DiaryResponseDto> getDiary(@RequestAttribute("username") String username)
    {
        return diaryService.getDiary(username);
    }


    @PostMapping("/postDiary")
    public int postDiary(@RequestBody DiaryResponseDto req, @RequestAttribute("username") String username)
    {
        return diaryService.postDiary(req, username);
    }

    @PostMapping("/updateDiary")
    public int updateDiary(@RequestBody Map<String, String> req)
    {
        return diaryService.updateDiary(req);
    }
}
