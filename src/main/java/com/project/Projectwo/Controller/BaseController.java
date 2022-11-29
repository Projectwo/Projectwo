package com.project.Projectwo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
    
    @RequestMapping("/")
    public String root(){
        return "member/login";
    }

    @RequestMapping("/member/main")
    public String studentMain(){
        return "member/member_main";
    }

    @RequestMapping("/academy/main")
    public String academyMain(){
        return "academy/academy_main";
    }


}
