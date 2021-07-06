package com.hiwijaya.springsecurity.controller;

import com.hiwijaya.springsecurity.ResponseHandler;
import com.hiwijaya.springsecurity.model.LoginRequest;
import com.hiwijaya.springsecurity.model.LoginResponse;
import com.hiwijaya.springsecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Happy Indra Wijaya
 */
@Controller
public class ApiController {

    @Autowired
    private AuthService authService;


    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){

        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());

        LoginResponse data = LoginResponse.builder()
                .username(loginRequest.getUsername())
                .token(token)
                .build();

        return new ResponseHandler(data).getResponse();
    }

}
