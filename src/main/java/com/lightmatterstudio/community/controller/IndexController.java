package com.lightmatterstudio.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Yafei
 * 2021/5/22
 */
@Controller
public class IndexController {


    @GetMapping("/")
    public String index(){
        return "index";
    }
}
