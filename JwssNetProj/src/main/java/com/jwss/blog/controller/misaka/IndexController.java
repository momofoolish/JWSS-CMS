package com.jwss.blog.controller.misaka;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/website/misaka")
    public String index(){
        return "misaka";
    }
}
