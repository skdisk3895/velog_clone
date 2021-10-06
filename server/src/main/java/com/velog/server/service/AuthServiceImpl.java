package com.velog.server.service;

import com.velog.server.domain.entity.User;
import com.velog.server.dto.LoginDTO;
import com.velog.server.dto.SignupDTO;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.service.ecryption.PasswordEncryption;
import com.velog.server.service.validation.AuthValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public String signup(SignupDTO signupDTO) {
        try {
            String email = signupDTO.getEmail();
            String password = signupDTO.getPassword();
            String passwordConfirm = signupDTO.getPasswordConfirm();

            if (!AuthValidation.checkEmailValidation(email)) return "Email validation error";
            if (!AuthValidation.checkEmailExists(email)) return "Email exists error";
            if (!AuthValidation.checkPasswordValidation(password)) return "Short password";
            if (!AuthValidation.checkPasswordConfirm(password, passwordConfirm)) return "Not same password";

            userRepository.save(signupDTO.toEntity());
        } catch (Exception e) {
            System.out.println(e);
        }

        return "Success Signup";
    }

    @Transactional
    public String login(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        User user = userRepository.findByEmail(email);

        if (user != null) {
            String salt = user.getSalt();
            try {
                if (!PasswordEncryption.getEncryptPasswordBySalt(password, salt).equals(user.getPassword()))
                    return "Password Error";
            } catch (NoSuchAlgorithmException e) {
                System.out.println("I'm sorry, but MD5 is not a valid message digest algorithm");
            }
        } else {
            return "Email exists error";
        }

        return "Success Login";
    }
}
