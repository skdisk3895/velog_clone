package com.velog.server.dto;

import com.velog.server.ecryption.PasswordEncryption;

public class SignupDTO {
    private Long id;
    private String email;
    private String password;
    private String passwordConfirm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void toEntity() {
        String encryptedPassword = PasswordEncryption.encryptPassword(password);
        System.out.println(encryptedPassword);
//        return new SignupEntity(null, , )
    }
}
