package com.toyproject.springbootbackend.controller;

import com.toyproject.springbootbackend.model.ActivitySchedule;
import com.toyproject.springbootbackend.repository.ChildRepository;
import com.toyproject.springbootbackend.repository.SeniorCitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TOYController {
    @Autowired
    private SeniorCitizenRepository seniorRepo;

    @Autowired
    private ChildRepository childRepo;

    @GetMapping("/toysystem")
    public String viewHomePage() {
//        System.out.println("Home Page that directs to Child Management and Senior Management");
        return "index";
    }

    @GetMapping("/toyadmin")
    public String showActivityManager() {
        return "activity_management";
    }


}
