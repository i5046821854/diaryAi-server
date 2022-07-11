package com.aidiary.demo.controller;

import com.aidiary.demo.dto.DiaryResponseDto;
import com.aidiary.demo.exception.DiaryException;
import com.aidiary.demo.service.DiaryService;
import com.aidiary.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DiaryController {


    private final DiaryService diaryService;

    @ExceptionHandler(value = DiaryException.class)
    public Map<String, String> controllerExceptionHanlder(Exception e)
    {
        Map<String, String> result = new HashMap<>();
        result.put("error", e.getMessage());
        return result;
    }

    @GetMapping("/getDiary")
    public List<DiaryResponseDto> getDiary(@RequestAttribute("username") String username)
    {
        return diaryService.getDiary(username);
    }


    @PostMapping("/postDiary")
    public DiaryResponseDto postDiary(@RequestBody DiaryResponseDto req, @RequestAttribute("username") String username)
    {
        return diaryService.postDiary(req, username);
    }

    @PostMapping("/updateDiary")
    public DiaryResponseDto updateDiary(@RequestBody Map<String, String> req)
    {
        return diaryService.updateDiary(req);
    }
}
