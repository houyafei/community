package com.lightmatterstudio.community.provider;

import com.alibaba.fastjson.JSON;
import com.lightmatterstudio.community.dto.access.AccessTokenDto;
import com.lightmatterstudio.community.dto.access.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Yafei
 * 2021/5/23
 */

@Component
public class GithubProvider {


    public String getAccessToken(AccessTokenDto accessTokenDto) {

        MediaType JSON
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        String json = com.alibaba.fastjson.JSON.toJSONString(accessTokenDto);


        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String bodyString = response.body().string();
            System.out.println(bodyString);
            return bodyString.split("&")[0].split("=")[1];
        } catch (IOException e) {

        }

        return null;
    }

    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {

            String bodyString = response.body().string();
            System.out.println(bodyString);
            return JSON.parseObject(bodyString,GitHubUser.class);
        } catch (IOException e) {

        }

        return null;

    }

}
