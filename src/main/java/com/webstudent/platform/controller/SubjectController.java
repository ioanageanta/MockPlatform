package com.webstudent.platform.controller;

import com.webstudent.platform.model.Exam;
import com.webstudent.platform.model.Subject;
import com.webstudent.platform.model.User;
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


        return subjectRepository.save(subject);
    }

    @PostMapping("/fulfill")
    public String postFulfillement() {
        return "{\n" +
                "\"fulfillmentText\": \"HELLLLOOO\",\n" +
                "\"fulfillmentMessages\": [\n" +
                "  {\n" +
                "    \"card\": {\n" +
                "      \"title\": \"card title\",\n" +
                "      \"subtitle\": \"card text\",\n" +
                "      \"imageUri\": \"https://assistant.google.com/static/images/molecule/Molecule-Formation-stop.png\",\n" +
                "      \"buttons\": [\n" +
                "        {\n" +
                "          \"text\": \"button text\",\n" +
                "          \"postback\": \"https://assistant.google.com/\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "],\n" +
                "\"source\": \"example.com\",\n" +
                "\"payload\": {\n" +
                "  \"google\": {\n" +
                "    \"expectUserResponse\": true,\n" +
                "    \"richResponse\": {\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"simpleResponse\": {\n" +
                "            \"textToSpeech\": \"HELLLLOOO this is a simple response\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"facebook\": {\n" +
                "    \"text\": \"Hello, Facebook!\"\n" +
                "  },\n" +
                "  \"slack\": {\n" +
                "    \"text\": \"This is a text response for Slack.\"\n" +
                "  }\n" +
                "},\n" +
                "\"outputContexts\": [\n" +
                "  {\n" +
                "    \"name\": \"projects/${PROJECT_ID}/agent/sessions/${SESSION_ID}/contexts/context name\",\n" +
                "    \"lifespanCount\": 5,\n" +
                "    \"parameters\": {\n" +
                "      \"param\": \"param value\"\n" +
                "    }\n" +
                "  }\n" +
                "],\n" +
                "\"followupEventInput\": {\n" +
                "  \"name\": \"event name\",\n" +
                "  \"languageCode\": \"en-US\",\n" +
                "  \"parameters\": {\n" +
                "    \"param\": \"param value\"\n" +
                "  }\n" +
                "}";
    }
}
