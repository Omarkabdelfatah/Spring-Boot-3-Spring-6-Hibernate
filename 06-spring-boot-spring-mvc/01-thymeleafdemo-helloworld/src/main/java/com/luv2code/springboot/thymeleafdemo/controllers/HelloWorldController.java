package com.luv2code.springboot.thymeleafdemo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // method to show the initial form
    @RequestMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    // method to process HTML form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    // method to read form data and add data to model

    @RequestMapping("/processFormVersionTwo")
    public String letShoutDude(HttpServletRequest request , Model model){

        // read request parameter from HTML form
        String theName = request.getParameter("studentName");

        // Capitalize the data
        theName = theName.toUpperCase();

        // create the message
        String result = "Yo! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }

    @RequestMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName
                                            , Model model){

        // Capitalize the data
        theName = theName.toUpperCase();

        // create the message
        String result = "Hey V3 !  " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }


}
