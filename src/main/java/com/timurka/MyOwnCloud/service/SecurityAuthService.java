package com.timurka.MyOwnCloud.service;

import com.timurka.MyOwnCloud.entity.User;
import com.timurka.MyOwnCloud.data.UserData;
import org.springframework.http.HttpStatus;

public interface SecurityAuthService {
    String geJwtTokenByLoginAndPassword(UserData userDto);
    HttpStatus deleteTokenAndLogout(String token);
    boolean validateUser(UserData userDto, User user);
}