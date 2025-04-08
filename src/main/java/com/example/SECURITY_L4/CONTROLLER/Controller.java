package com.example.SECURITY_L4.CONTROLLER;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/Hello")
    public String hello(){
        return "Hello Your Are Authenticated";
    }
}
