package com.program.pyohemia.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String tologin(){
        return "login";
    }
    @RequestMapping("/reg")
    public String reg(){
        return "register";
    }

}
