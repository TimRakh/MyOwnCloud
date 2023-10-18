package com.timurka.MyOwnCloud.data;

import lombok.Data;

@Data
public class Error {

    private String message;
    private int id;

    public Error(String message, int id) {
        this.message = message;
        this.id = id;
    }
}
