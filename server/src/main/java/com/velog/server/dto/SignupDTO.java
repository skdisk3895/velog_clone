package com.velog.server.dto;

import com.velog.server.domain.entity.User;
import com.velog.server.service.ecryption.PasswordEncryption;
import lombok.Getter;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SignupDTO {
    private Long id;
    private String email;
    private String password;
    private String passwordConfirm;

    public User toEntity() {
        List<String> encryptedString = new ArrayList<String>();

        try {
            encryptedString = PasswordEncryption.encryptPassword(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("I'm sorry, but MD5 is not a valid message digest algorithm");
        }
        
        return new User(null, email, encryptedString.get(0), encryptedString.get(1), null, null);
    }
}
