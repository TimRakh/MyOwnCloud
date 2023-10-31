package com.timurka.MyOwnCloud.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenData {
    @JsonProperty("auth-token")
    private String token;



    @Override
    public String toString() {
        return "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return this.token;
    }
}