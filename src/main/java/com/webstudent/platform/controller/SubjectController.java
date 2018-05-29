package com.webstudent.platform.controller;

import com.webstudent.platform.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/getGrade/{email}/{examName}")
    public Integer getGrade(@PathVariable("email") String email, @PathVariable("examName") String examName) {
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
