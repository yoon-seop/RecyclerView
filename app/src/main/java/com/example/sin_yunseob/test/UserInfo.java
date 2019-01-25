package com.example.sin_yunseob.test;

public class UserInfo {

    private String user_name;
    private String user_image_url;

    private String post_name;
    private String post_description;
    private String post_stargazers_count;

    public UserInfo(String user_name, String user_image_url, String post_name, String post_description, String post_stargazers_count) {
        this.user_name = user_name;
        this.user_image_url = user_image_url;
        this.post_name = post_name;
        this.post_description = post_description;
        this.post_stargazers_count = post_stargazers_count;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_image_url() {
        return user_image_url;
    }

    public void setUser_image_url(String user_image_url) {
        this.user_image_url = user_image_url;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPost_description() {
        return post_description;
    }

    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }

    public String getPost_stargazers_count() {
        return post_stargazers_count;
    }

    public void setPost_stargazers_count(String post_stargazers_count) {
        this.post_stargazers_count = post_stargazers_count;
    }
}
