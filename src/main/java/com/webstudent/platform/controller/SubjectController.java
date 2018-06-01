package com.webstudent.platform.controller;

import com.webstudent.platform.model.Exam;
import com.webstudent.platform.model.Subject;
import com.webstudent.platform.model.User;
import com.webstudent.platform.notifications.NotificationData;
import com.webstudent.platform.notifications.NotificationRequest;
import com.webstudent.platform.notifications.Utils;
import com.webstudent.platform.repository.ExamRepository;
import com.webstudent.platform.repository.SubjectRepository;
import com.webstudent.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExamRepository examRepository;

    @GetMapping("/getGrade/{email}/{examName}")
    public Integer getGrade(@PathVariable("email") String email, @PathVariable("examName") String examName) {
        examName = examName.replace("%20", " ");
        return subjectRepository.findGrade(email, examName);
    }

    @GetMapping("/getRetests")
    public Integer getRetests() {
        return subjectRepository.countRetests();
    }

    @GetMapping("/getAbsences")
    public Integer getAbsences() {
        return subjectRepository.countAbsences();
    }

    @GetMapping("/getExams/{email}")
    public List<Exam> getExams(@PathVariable("email") String email) {
        return subjectRepository.getExams(email);
    }

    @GetMapping("/getExam/{id}")
    public Exam getExam(@PathVariable("id") Integer id) {
        return subjectRepository.getExam(id);
    }

    @PostMapping("/saveGrade")
    public Subject saveGrade(@RequestBody Subject subject) {
        User user = userRepository.findById(subject.getUser().getId()).orElse(null);
        Exam exam = Optional.ofNullable(examRepository.findExamBySubject(subject.getExam().getName())).orElse(subject.getExam());

        subject.setId(Optional.ofNullable(subjectRepository.getSubjectByUserAndExam(user.getId(), exam.getId())).orElse(subject).getId());

        subject.setExam(exam);
        subject.setUser(user);

        NotificationData notificationData = new NotificationData();
        notificationData.setMessage("You got a " + subject.getGrade() + " on " + subject.getExam().getName());

        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setData(notificationData);

        Utils.sendPost(notificationRequest);
        Utils.sendMail(subject.getGrade());
        return subjectRepository.save(subject);
    }
}
