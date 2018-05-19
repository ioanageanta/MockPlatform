package com.webstudent.platform.controller;

import com.webstudent.platform.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExamController {
    @Autowired
    private ExamRepository examRepository;

    @GetMapping("/getDate/{subject}")
    public String getDate(@PathVariable("subject") String subject) {
        return examRepository.findBySubject(subject);
    }
}
