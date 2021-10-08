package com.velog.server.domain;

import com.velog.server.domain.entity.Hashtag;
import com.velog.server.domain.entity.Post;
import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.HashtagRepositoty;
import com.velog.server.domain.repository.PostRepository;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PostTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HashtagRepositoty hashtagRepositoty;
    @Autowired
    private PostService postService;

    @Test
    @Transactional
    @Rollback(false)
    void create() {
        Post post = new Post();

        String title = "title1234";
        String content = "content1234";
        List<String> hashtags = new ArrayList<>();
        String email = "test@test.com";
        hashtags.add("spring22");
        hashtags.add("summer22");
        User user = userRepository.findByEmail(email);

        post.setUser(user);
        post.setTitle(title);
        post.setContent(content);

        for (String tag : hashtags) {
            Hashtag hashtag = new Hashtag();
            if (hashtagRepositoty.findByName(tag) == null) {
                hashtag.setName(tag);
                post.getHashtags().add(hashtag);
            } else {
                post.getHashtags().add(hashtagRepositoty.findByName(tag));
            }

            hashtag.getPosts().add(post);
        }

        postRepository.save(post);
    }

    @Test
    @Transactional
    void readPost() {
//        Post post = postRepository.findById(20L).get();
//        System.out.println(post.getId());
//        System.out.println(post.getUser().getEmail());
//
//        System.out.println(post.getPostHashtags().size());
//
//        for (PostHashtag hashtag : post.getPostHashtags()) {
//            System.out.println(hashtag.getHashtag().getName());
//        }
    }
}
