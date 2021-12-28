package org.csu.userservice.controller;

import org.csu.userservice.domain.UserPerson;
import org.csu.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class UserPersonController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/User/findUserPerson", method = RequestMethod.POST)
    public UserPerson findUserPersonByEmailAndPwd(String email, String password, HttpServletRequest request){
        return userService.findUserPersonByEmailAndPwd("\""+email+"\"", password);
    }

    @RequestMapping(value = "/User/UserRegister", method = RequestMethod.POST)
    public String registered(UserPerson userPerson){
        try{
            userService.insertUserPerson(userPerson);
            return "success";
        }
        catch (Exception e){
            System.out.print(e);
            return "false";
        }
    }

    

}
