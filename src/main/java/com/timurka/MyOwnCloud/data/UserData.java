package com.timurka.MyOwnCloud.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class UserData {
    //  @NotBlank
    @JsonProperty("login")
    private String login;
    //@NotBlank
    @JsonProperty("password")
    private String password;

    public UserData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }
}