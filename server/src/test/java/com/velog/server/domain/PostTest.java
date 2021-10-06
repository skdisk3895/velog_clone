package com.velog.server.domain;

import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.PostRepository;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.dto.PostDTO;
import com.velog.server.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
class PostTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostService postService;

    @Test
    @Transactional
    @Rollback(false)
    void create() {
        String email = "test22@test.com";
        String title = "title123";
        String content = "content123";

//        List<String> hashtag = new ArrayList<>();
//        hashtag.add("도롱");
//        hashtag.add("므앙");

        User user =  userRepository.findByEmail(email);
        System.out.println(email);

        System.out.println(user);

        PostDTO postDTO = new PostDTO();

        postDTO.setTitle(title);
        postDTO.setContent(content);
//        postDTO.setTags(hashtag);
        postDTO.setUser(user);
        postService.createPost(postDTO);
    }
}
