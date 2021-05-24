package com.lightmatterstudio.community.controller;

import com.lightmatterstudio.community.dto.access.AccessTokenDto;
import com.lightmatterstudio.community.dto.access.GitHubUser;
import com.lightmatterstudio.community.mappers.GithubUserMappers;
import com.lightmatterstudio.community.model.User;
import com.lightmatterstudio.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Yafei
 * 2021/5/23
 */

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private GithubUserMappers githubUserMappers;

    @Value("${github.client.id}")
    private String githubClientId;

    @Value("${github.client.secret}")
    private String githubClientSecret;

    @Value("${github.redirect.uri}")
    private String githubRedirectUri;

    @GetMapping("callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletResponse response) {

        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(githubClientId);
        accessTokenDto.setRedirect_uri(githubRedirectUri);
        accessTokenDto.setClient_secret(githubClientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GitHubUser gitHubUser = githubProvider.getUser(accessToken);

        if (gitHubUser != null && gitHubUser.getId()!=0) {

            User dbUser = githubUserMappers.getUser(""+gitHubUser.getId());
            System.out.println("dbUser is null " + dbUser);
            if (dbUser==null ){
                User user = new User();
                user.setToken(UUID.randomUUID().toString());
                user.setAccountId("" + gitHubUser.getId());
                user.setName(gitHubUser.getName());
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                dbUser = user;
                githubUserMappers.insertUser(user);
            }
            response.addCookie(new Cookie("token",dbUser.getToken()));

        } else {

        }

        // 重定向到 index
        return "redirect:/";
    }

}
