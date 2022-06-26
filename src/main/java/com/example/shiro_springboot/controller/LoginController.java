package com.example.shiro_springboot.controller;

import com.example.shiro_springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }
    @RequestMapping("/login")
    public ModelAndView getIndex(String username, String password, ModelAndView modelAndView){

        try {
            loginService.checkLogin(username,password);
            modelAndView.addObject("name",username);
            modelAndView.addObject("pass",password);
            modelAndView.setViewName("index");
        } catch (Exception e) {
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
