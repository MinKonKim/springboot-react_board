package com.minkon.board.controller;

import com.minkon.board.dto.ResponseDto;
import com.minkon.board.dto.SignInDto;
import com.minkon.board.dto.SignInResponseDto;
import com.minkon.board.dto.SignUpDto;
import com.minkon.board.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/api/auth/signUp")
    public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {
        ResponseDto<?> result = authService.signUp(requestBody);
        return result;
    }

    @PostMapping("/api/auth/signIn")
    public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody) {
        System.out.println("SignIn 호출됨.");
        ResponseDto<SignInResponseDto> result = authService.signIn(requestBody);
        return result;
    }

}