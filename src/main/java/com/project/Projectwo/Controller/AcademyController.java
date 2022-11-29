package com.project.Projectwo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AcademyController {

    // by 조성빈, academy main template
    @RequestMapping("/main")
    public String academyMain(){
        return "academy/academy_main";
    }

    
}
