package com.minkon.board.controller;

import com.minkon.board.dto.ResponseDto;
import com.minkon.board.dto.SignInDto;
import com.minkon.board.dto.SignUpDto;
import com.minkon.board.service.AuthService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody){
        ResponseDto<?> result = authService.signUp(requestBody);
        return result;
    }
}

