package com.velog.server.controller;

import com.velog.server.dto.LoginDTO;
import com.velog.server.dto.SignupDTO;
import com.velog.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> ResponseSignup(@RequestBody SignupDTO signupDTO) {
        String message = authService.signup(signupDTO);

        if (message.equals("Success Signup")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> ResponseLogin(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO.getEmail() + " " + loginDTO.getPassword());
        String message = authService.login(loginDTO);

        if (message.equals("Success Login")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
        }
    }
}
