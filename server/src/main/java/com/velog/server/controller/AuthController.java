package com.velog.server.controller;

import com.velog.server.dto.LoginDTO;
import com.velog.server.dto.SignupDTO;
import com.velog.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String ResponseSignup(@RequestBody SignupDTO signupDTO) {
//        System.out.println(signupDTO.getId());
//        System.out.println(signupDTO.getUsername());
//        System.out.println(signupDTO.getPassword());
//        System.out.println(signupDTO.getPasswordConfirm());
        authService.signup(signupDTO);
        return "signup";
    }

    @PostMapping("/login")
    public String ResponseLogin(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO.getEmail());
        System.out.println(loginDTO.getPassword());
        return "login";
    }
}
