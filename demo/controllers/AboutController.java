package com.example.demo.controllers;

import com.example.demo.domain.AboutInfo;
import com.example.demo.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    private final AboutService aboutService;

    @Autowired
    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @GetMapping("/about.html")
    public String about(Model model) {
        AboutInfo aboutInfo = aboutService.getAboutInfo();
        model.addAttribute("aboutInfo", aboutInfo);
        return "about";
    }

}
