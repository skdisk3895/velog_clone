package com.velog.server.service;

import com.velog.server.domain.entity.User;
import com.velog.server.dto.auth.LoginDTO;
import com.velog.server.dto.auth.SignupDTO;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.service.ecryption.PasswordEncryption;
import com.velog.server.service.validation.AuthValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public String signup(SignupDTO signupDTO) {
        String email = signupDTO.getEmail();
        String password = signupDTO.getPassword();
        String passwordConfirm = signupDTO.getPasswordConfirm();

        AuthValidation authValidation = new AuthValidation();

        if (!authValidation.checkEmailValidation(email)) return "Email validation error";
        if (authValidation.checkEmailExists(email, userRepository)) return "Email exists error";
        if (!authValidation.checkPasswordValidation(password)) return "Short password";
        if (!authValidation.checkPasswordConfirm(password, passwordConfirm)) return "Not same password";

        List<String> encryptedString = new ArrayList<String>();

        try {
            encryptedString = PasswordEncryption.encryptPassword(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("I'm sorry, but MD5 is not a valid message digest algorithm");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(encryptedString.get(0));
        user.setSalt(encryptedString.get(1));
        userRepository.save(user);

        return "Success Signup";
    }

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
