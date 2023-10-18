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

    public TokenData(String token) {
        this.token=token;
    }

    @Override
    public String toString() {
        return "token='" + token + '\'' +
                '}';
    }
}