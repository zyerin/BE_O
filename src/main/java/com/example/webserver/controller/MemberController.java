package com.example.webserver.controller;

import com.example.webserver.login.JwtToken;
import com.example.webserver.login.SignInDto;
import com.example.webserver.login.SignUpDto;
import com.example.webserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/test")
    private String test(){
        return "test ok";
    }

    @PostMapping("/sign-up")
    public String signup(@RequestBody SignUpDto signUpDto){
        String username = signUpDto.getUsername();
        String password = signUpDto.getPassword();
        String result = memberService.signUp(username,password);
        log.info("회원가입 결과 :"+result);
        return result;
    }

    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody SignInDto signInDto) {
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();
        JwtToken jwtToken = memberService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }


}
