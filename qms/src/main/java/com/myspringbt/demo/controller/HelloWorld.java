package com.myspringbt.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("v1/")
@Api("HelloWorldController")
public class HelloWorld {
    @GetMapping("helloworld")
    public String hellows() {
        new String();
        return "hello world";
    }

    
}
