package com.velog.server.service;

import com.velog.server.domain.entity.User;
import com.velog.server.dto.LoginDTO;
import com.velog.server.dto.SignupDTO;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.service.ecryption.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    // 이메일 유효성 체크
    public boolean checkEmailValidation(String email) {
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // 이메일 존재여부 체크
    public boolean checkEmailExists(String email) {
        return userRepository.findByEmail(email) == null;
    }

    // 비밀번호 유효성 체크
    public boolean checkPasswordValidation(String password) {
        return password.length() >= 8;
    }

    // 비밀번호 랑 비밀번호 확인이 같은지 체크
    public boolean checkPasswordConfirm(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    @Transactional
    public String signup(SignupDTO signupDTO) {
        try {
            String email = signupDTO.getEmail();
            String password = signupDTO.getPassword();
            String passwordConfirm = signupDTO.getPasswordConfirm();

            if (!this.checkEmailValidation(email)) return "Email validation error";
            if (!this.checkEmailExists(email)) return "Email exists error";
            if (!this.checkPasswordValidation(password)) return "Short password";
            if (!this.checkPasswordConfirm(password, passwordConfirm)) return "Not same password";

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
                if (!PasswordEncryption.encryptPasswordBySalt(password, salt).equals(user.getPassword()))
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
