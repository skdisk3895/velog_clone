package com.velog.server;

import com.velog.server.entity.Users;
import com.velog.server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void signup() {
        List<Users> users = userRepository.findAll();
    }
}
