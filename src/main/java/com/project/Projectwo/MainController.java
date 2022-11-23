package com.project.Projectwo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String root(){
        return "index";
    }

    @RequestMapping("/signup")
    public String signup(){
        return "member/signup";
    }
    
    @RequestMapping("/login")
    public String login(){
        return "member/login";
    }
}
