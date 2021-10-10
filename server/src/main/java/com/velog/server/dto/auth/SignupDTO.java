package com.velog.server.dto.auth;

import com.velog.server.domain.entity.Comment;
import com.velog.server.domain.entity.Post;
import com.velog.server.domain.entity.User;
import com.velog.server.service.ecryption.PasswordEncryption;
import lombok.Getter;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SignupDTO {
    private Long id;
    private String email;
    private String password;
    private String passwordConfirm;

}
