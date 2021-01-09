package com.jwss.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/blog")
    public String simple(){
        return "Hello JWSS_CMS";
    }
}
