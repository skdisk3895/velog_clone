package com.velog.server.service;

import com.velog.server.dto.auth.LoginDTO;
import com.velog.server.dto.auth.SignupDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public String signup(SignupDTO signupDTO);
    public String login(LoginDTO loginDTO);
}
