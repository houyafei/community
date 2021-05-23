package com.lightmatterstudio.community.controller;

import com.lightmatterstudio.community.mappers.GithubUserMappers;
import com.lightmatterstudio.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Yafei
 * 2021/5/22
 */
@Controller
public class IndexController {

    @Autowired
    private GithubUserMappers githubUserMappers;

    @GetMapping("/")
    public String index(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                System.out.println("token in index:   "+token);
                if(token==null || token.equals("")){
                    return "index";
                }else{
                    User user = githubUserMappers.getUser(token);
                    System.out.println("get user "+ user);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
//                        return "redirect:/";
                    }
                }
                break;
            }
        }
        
        
        return "index";
    }
}
