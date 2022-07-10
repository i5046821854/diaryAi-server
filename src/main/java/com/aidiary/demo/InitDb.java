package com.aidiary.demo;

import com.aidiary.demo.domain.Diary;
import com.aidiary.demo.domain.User;
import com.aidiary.demo.repository.DiaryRepository;
import com.aidiary.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final UserRepository userRepository;
        private final DiaryRepository diaryRepository;
        public void dbInit(){
            List<String> grades = new ArrayList<>();
            grades.add("USER");
            User user = new User("dldudtls", "123", grades);
            userRepository.save(user);
            Diary diary = new Diary(1, "dldudtls", "title", "contents", 5.0f, 4.5f, 4.0f, LocalDateTime.now());
            diaryRepository.save(diary);
        }
    }
}
