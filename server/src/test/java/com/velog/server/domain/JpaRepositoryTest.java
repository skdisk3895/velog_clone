package com.velog.server.domain;

import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class JpaRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
//    @Transactional
//    @Rollback(false)
    void save() {
        String name = "user";
        userRepository.save(User.builder().name(name).build());

        List<User> users = userRepository.findAll();

        User user = users.get(0);
        System.out.println(user);
        assertThat(user.getName()).isEqualTo(name);
    }
}
