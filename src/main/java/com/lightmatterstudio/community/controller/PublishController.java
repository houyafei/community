package com.lightmatterstudio.community.controller;

import com.lightmatterstudio.community.mappers.GithubUserMappers;
import com.lightmatterstudio.community.mappers.QuestionMapper;
import com.lightmatterstudio.community.model.Question;
import com.lightmatterstudio.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Yafei
 * 2021/5/24
 */

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private GithubUserMappers githubUserMappers;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title==null || title.trim().equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        if(description==null || description.trim().equals("")){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }

        if(tag==null || tag.trim().equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    System.out.println("token in index:   " + token);
                    if (token == null || token.equals("")) {
                        return "index";
                    } else {
                         user = githubUserMappers.getUser(token);
                        System.out.println("get user " + user);

                    }
                    break;
                }
            }
        }

        long createTime = System.currentTimeMillis();

        Question question = new Question(title, description, createTime, createTime, -1, tag);


        if (user == null){
            model.addAttribute("error","用户未登录, 转为匿名用户发布");
//            return "publish";
        }else{
            question.setCreator(user.getId());
        }

//        request.getSession().setAttribute("question", question);
        questionMapper.createQuestion(question);
        return "redirect:/";
    }
}
