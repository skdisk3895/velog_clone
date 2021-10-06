package com.velog.server.service;

import com.velog.server.dto.LoginDTO;
import com.velog.server.dto.SignupDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public String signup(SignupDTO signupDTO);
    public String login(LoginDTO loginDTO);
}
