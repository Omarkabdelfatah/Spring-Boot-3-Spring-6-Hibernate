package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

//    inject properties for:coach.name and team.name
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/teamInfo")
    public String teamInfo(){
        return "Coach " + coachName + " Team Name " + teamName;
    }
    @GetMapping("/")
    public String SayHello(){
        return "Hello World!";
    }
    @GetMapping("/omar")
    public String SayName(){
        return "I Am Omar";
    }
}
