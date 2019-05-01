package com.example.songr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello World.";
    }

    @GetMapping("/capitalize/{input}")
    @ResponseBody
    public String capitalize(@PathVariable String input) {
        return input.toUpperCase();
    }
}
