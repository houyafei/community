package com.lightmatterstudio.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Yafei
 * 2021/5/22
 */

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name")String name, Model model){

        model.addAttribute("name",name);

        // 此时自动到模板目录查找相应的模板
        return "hello";

    }
}
