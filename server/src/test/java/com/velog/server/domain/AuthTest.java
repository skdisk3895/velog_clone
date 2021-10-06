package com.velog.server.domain;

import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.dto.LoginDTO;
import com.velog.server.dto.SignupDTO;
import com.velog.server.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    @Test
    @Transactional
    void signup() {
        String email = "test@test.com";
        String password = "12345678";
        String passwordConfirm = "12345678";

        SignupDTO signupDTO = new SignupDTO();

        signupDTO.setEmail(email);
        signupDTO.setPassword(password);
        signupDTO.setPasswordConfirm(passwordConfirm);

        String message = authService.signup(signupDTO);
        System.out.println(message);

        List<User> users = userRepository.findAll();

        User user = users.get(0);
        System.out.println(user.getId());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getSalt());
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    @Transactional
    void login() {
        String email = "test@test.com";
        String password = "12345678";

        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail(email);
        loginDTO.setPassword(password);

        String message = authService.login(loginDTO);
        System.out.println(message);
    }
}
