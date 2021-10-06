package com.velog.server.service.validation;

import com.velog.server.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthValidation {

    @Autowired
    static private UserRepository userRepository;

    // 이메일 유효성 체크
    static public boolean checkEmailValidation(String email) {
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // 이메일 존재여부 체크
    static public boolean checkEmailExists(String email) {
        return userRepository.findByEmail(email) == null;
    }

    // 비밀번호 유효성 체크
    static public boolean checkPasswordValidation(String password) {
        return password.length() >= 8;
    }

    // 비밀번호 랑 비밀번호 확인이 같은지 체크
    static public boolean checkPasswordConfirm(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }
}
