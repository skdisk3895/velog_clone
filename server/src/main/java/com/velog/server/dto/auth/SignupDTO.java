package com.velog.server.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    private String email;
    private String password;
    private String passwordConfirm;

}
