package com.velog.server.service;

import com.velog.server.dto.LoginDTO;
import com.velog.server.dto.SignupDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public boolean checkEmailValidation(String username);
    public boolean checkEmailExists(String username);
    public void checkPasswordValidation(String password);
    public boolean checkPasswordConfirm(String password, String passwordConfirm);
    public String signup(SignupDTO signupDTO);
    public String login(LoginDTO loginDTO);
}
