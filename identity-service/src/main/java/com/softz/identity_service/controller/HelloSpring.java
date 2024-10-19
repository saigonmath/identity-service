package com.softz.identity_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpring {

    @GetMapping("hello")
    String hello(){
        return "Hello Spring Boot";
    }
}
