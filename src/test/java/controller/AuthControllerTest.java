package controller;
import com.timurka.MyOwnCloud.controller.AuthController;

import com.timurka.MyOwnCloud.data.TokenData;
import com.timurka.MyOwnCloud.data.UserData;
import com.timurka.MyOwnCloud.service.SecurityAuthService;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class AuthControllerTest {
    @Mock
    SecurityAuthService service;
    @Mock
    Logger log;
    @InjectMocks
    AuthController authUserController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin(){
        when(service.geJwtTokenByLoginAndPassword(new UserData("login", "password"))).thenReturn("geJwtTokenByLoginAndPasswordResponse");

        TokenData result = authUserController.login(new UserData("login", "password"));
        Assertions.assertEquals("geJwtTokenByLoginAndPasswordResponse", result.getToken());
    }

    @Test
    void testLogout(){
        when(service.deleteTokenAndLogout(anyString())).thenReturn(HttpStatus.OK);

        HttpStatus result = authUserController.logout("token");
        Assertions.assertEquals(HttpStatus.OK, result);
    }
}
