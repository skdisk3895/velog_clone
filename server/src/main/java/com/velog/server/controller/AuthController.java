package com.velog.server.controller;

import com.velog.server.dto.LoginDTO;
import com.velog.server.dto.SignupDTO;
import com.velog.server.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. User auth"})
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "회원가입", notes = "회원가입을 요청한다.")
    @PostMapping("/signup")
    public ResponseEntity<String> ResponseSignup(@ApiParam(value = "회원가입 폼 객체", required = true) @RequestBody SignupDTO signupDTO) {
        String message = authService.signup(signupDTO);

        if (message.equals("Success Signup")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation(value = "로그인", notes = "로그인을 요청한다.")
    @PostMapping("/login")
    public ResponseEntity<String> ResponseLogin(@ApiParam(value = "로그인 폼 객체", required = true) @RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO.getEmail() + " " + loginDTO.getPassword());
        String message = authService.login(loginDTO);

        if (message.equals("Success Login")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
        }
    }
}
