package com.webstudent.platform.controller;

import com.webstudent.platform.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExamController {
    @Autowired
    private ExamRepository examRepository;
//
//    @PostMapping("/saveDevice")
//    public void saveDevice(@RequestBody Device device) {
//        examRepository.save(device);
//    }
//
//    @GetMapping("/getDevice/{id}")
//    public boolean isDevicePresent(@PathVariable("id") String id) {
//        return examRepository.findDevice(id) != null;
//    }
}
