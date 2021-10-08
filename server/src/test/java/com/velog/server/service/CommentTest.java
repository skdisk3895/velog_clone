package com.velog.server.service;

import com.velog.server.domain.entity.Comment;
import com.velog.server.domain.entity.Post;
import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.CommentRepository;
import com.velog.server.domain.repository.PostRepository;
import com.velog.server.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class CommentTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    void 댓글작성() {
        String content = "댓글 111";
        String email = "test@test.com";

        User user = userRepository.findByEmail(email);
        Post post = postRepository.findById(24L).get();

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);
    }
}
