package com.webstudent.platform.controller;

import com.webstudent.platform.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/getGrade/{email}")
    public Integer getGrade(@PathVariable("email") String email) {
        return subjectRepository.findGrade(email);
    }

    @GetMapping("/getRetests")
    public Integer getRetests() {
        return subjectRepository.countRetests();
    }

    @GetMapping("/getAbsences")
    public Integer getAbsences() {
        return subjectRepository.countAbsences();
    }
}
