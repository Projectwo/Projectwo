package com.project.Projectwo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
    
    @RequestMapping("/")
    public String root(){
        return "index";
    }
}
