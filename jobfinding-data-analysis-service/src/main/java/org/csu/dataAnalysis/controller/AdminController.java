package com.h3c.jobhunter.controller;

import com.h3c.jobhunter.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by lenovo' on 2018/3/8.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private IUserService userService;

    @RequestMapping("/login")
    public String login(){
        return "loginpage/login";
    }


    @RequestMapping("/register")
    public String register(){
        return "loginpage/register";
    }


    @RequestMapping("/resume")
    public String createResume(){
        return "deputypage/resumemanage";
    }

    @RequestMapping("/signout")
    public String signOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        Enumeration enumeration = session.getAttributeNames();
        for(Enumeration e=enumeration;e.hasMoreElements();){
            String name = e.nextElement().toString();
            session.removeAttribute(name);
        }


        return "/loginpage/login";
    }
}
