package com.timurka.MyOwnCloud.service.impl;

import com.timurka.MyOwnCloud.authority.JwtTokenService;
import com.timurka.MyOwnCloud.entity.Token;
import com.timurka.MyOwnCloud.entity.User;
import com.timurka.MyOwnCloud.exceptions.UserNotFoundException;
import com.timurka.MyOwnCloud.data.UserData;
import com.timurka.MyOwnCloud.repository.UserRepository;
import com.timurka.MyOwnCloud.service.SecurityAuthService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
@Log4j2
@Service
public class SecurityAuthServiceImpl implements SecurityAuthService {

    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;


    @Autowired
    public SecurityAuthServiceImpl(JwtTokenService jwtTokenService, UserRepository userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }


    @Override
    public String geJwtTokenByLoginAndPassword(UserData userData) {
        User user = findUserByLoginAndPassword(userData.getLogin(),userData.getPassword());
        if (user != null) {
            if (user.getToken() != null) {
                return user.getToken();
            }
            if (validateUser(userData, user)) {
                String token = jwtTokenService.generateToken(user.getLogin());
                log.info("Token generate for user:" + user.getLogin() + " " + new Date(System.currentTimeMillis()));

                Token userToken = jwtTokenService.mapUserAndJwtTokenToToken(user, token);
                return jwtTokenService.saveTokenToDataBase(userToken);
            }
        }
        throw new UserNotFoundException("User not found with username: " + userData.getLogin());
    }

    @Override
    public HttpStatus deleteTokenAndLogout(String token) {

        jwtTokenService.removeTokenAndLogout(token.substring(7, token.length()));
        return HttpStatus.OK;
    }

    @Override
    public boolean validateUser(UserData userData, User user) {
        return userData.getLogin().equals(user.getLogin())
                || userData.getPassword().equals(user.getPassword());
    }

    @Transactional
    public User findUserByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).orElseThrow(() ->
                new UserNotFoundException("User not found with username: " + login + " and password"));
    }

    @Transactional
    public User findUserByLogin(String login){
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + login));
    }

}
