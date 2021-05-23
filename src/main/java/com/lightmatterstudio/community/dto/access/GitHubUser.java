package com.lightmatterstudio.community.dto.access;

/**
 * Yafei
 * 2021/5/23
 */


public class GitHubUser {

    private long id ;

    private String name;

    private String  bio;



    public GitHubUser() {
    }

    public GitHubUser(long id, String name, String bio) {
        this.id = id;
        this.name = name;
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
