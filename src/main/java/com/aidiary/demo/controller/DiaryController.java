package com.aidiary.demo.controller;

import com.aidiary.demo.dto.DiaryResponseDto;
import com.aidiary.demo.dto.UpdateResponseDto;
import com.aidiary.demo.exception.DiaryException;
import com.aidiary.demo.service.DiaryService;
import com.aidiary.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public List<UpdateResponseDto> getDiary(@RequestAttribute("username") String username)
    {
        return diaryService.getDiary(username);
    }


//    @PostMapping("/postDiary")
//    public UpdateResponseDto postDiary(@RequestBody DiaryResponseDto req, @RequestAttribute("username") String username)
//    {
//        return diaryService.postDiary(req, username);
//    }

    @PostMapping("/postDiary")
    public UpdateResponseDto postDiary(HttpEntity<String> req, @RequestAttribute("username") String username) throws IOException, InterruptedException {
        System.out.println(req.getBody());
        UpdateResponseDto res = diaryService.postDiary(req.getBody(), username);
        return res;
    }

    @PostMapping("/updateDiary")
    public UpdateResponseDto updateDiary(@RequestBody Map<String, String> req)
    {
        return diaryService.updateDiary(req);
    }
}
