package com.timurka.MyOwnCloud.controller;

import com.timurka.MyOwnCloud.data.UserData;
import com.timurka.MyOwnCloud.data.TokenData;
import com.timurka.MyOwnCloud.service.SecurityAuthService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class AuthController {
    SecurityAuthService service;

    public AuthController(SecurityAuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public TokenData login(@RequestBody @NonNull UserData userDto) {
        String token = service.geJwtTokenByLoginAndPassword(userDto);
        return new TokenData(token);
    }

    @PostMapping("/logout")
    public HttpStatus logout(@RequestHeader("auth-token") @NonNull String token) {
        service.deleteTokenAndLogout(token);
        return HttpStatus.OK;
    }
}
