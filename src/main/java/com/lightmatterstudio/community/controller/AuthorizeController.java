package com.lightmatterstudio.community.controller;

import com.lightmatterstudio.community.dto.access.AccessTokenDto;
import com.lightmatterstudio.community.dto.access.GitHubUser;
import com.lightmatterstudio.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Yafei
 * 2021/5/23
 */

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String githubClientId;

    @Value("${github.client.secret}")
    private String githubClientSecret;

    @Value("${github.redirect.uri}")
    private String githubRedirectUri;

    @GetMapping("callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state){

        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(githubClientId);
        accessTokenDto.setRedirect_uri(githubRedirectUri);
        accessTokenDto.setClient_secret(githubClientSecret);
        String  accessToken = githubProvider.getAccessToken(accessTokenDto);
        System.out.println("token: "+ accessToken);
        GitHubUser user = githubProvider.getUser(accessToken);
        System.out.println("user "+ user);
        return "index";
    }

}
