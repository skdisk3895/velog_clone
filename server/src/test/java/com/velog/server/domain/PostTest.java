package com.velog.server.domain;

import com.velog.server.domain.entity.Hashtag;
import com.velog.server.domain.entity.Post;
import com.velog.server.domain.entity.PostHashtag;
import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.HashtagRepositoty;
import com.velog.server.domain.repository.PostHashtagRepository;
import com.velog.server.domain.repository.PostRepository;
import com.velog.server.domain.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
    private PostHashtagRepository postHashtagRepository;
    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    void create() {
        Hashtag hashtag = new Hashtag();
        if (hashtagRepositoty.findByName("summer") != null) {
            hashtag = hashtagRepositoty.findByName("summer");
        } else {
            hashtag.setName("summer");
        }
        em.persist(hashtag);

        Post post = new Post();
        User user = userRepository.findByEmail("test@test.com");
        post.setTitle("title1236");
        post.setContent("content1234");
        post.setUser(user);
        em.persist(post);

        PostHashtag postHashtag = new PostHashtag();
        postHashtag.setPost(post);
        postHashtag.setHashtag(hashtag);

        post.getPostHashtags().add(postHashtag);
        hashtag.getPostHashtags().add(postHashtag);
        em.persist(postHashtag);

        postHashtagRepository.save(postHashtag);
    }
}
