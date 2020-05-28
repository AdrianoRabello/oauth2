package com.oauth2.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller

public class HomeController {

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public String teste(){
        return "teste";
    }

    @RequestMapping("/home")
    public String home(){

        return "home.jsp";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login.jsp";
    }


    @RequestMapping("/logout-sucess")
    public String logoutPage(){

        return "logout.jsp";
    }

    @RequestMapping("user")
    @ResponseBody
    public Principal user(Principal principal){

        return principal;
    }
}
