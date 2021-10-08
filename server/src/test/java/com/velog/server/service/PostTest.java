package com.velog.server.service;

import com.velog.server.domain.entity.Comment;
import com.velog.server.domain.entity.Hashtag;
import com.velog.server.domain.entity.Post;
import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.HashtagRepositoty;
import com.velog.server.domain.repository.PostRepository;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.dto.PostDTO;
import com.velog.server.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
    void 포스트작성() {
        PostDTO postDTO = new PostDTO();
        List<String> hashtags = new ArrayList<>();
        hashtags.add("qwe");
        hashtags.add("spring22");
        postDTO.setTitle("new title");
        postDTO.setContent("new content");
        postDTO.setEmail("test@test.com");
        postDTO.setHashtags(hashtags);

        Post post = postService.createPost(postDTO);

        // 좋아요 누르기
        User user = userRepository.findByEmail("test@test.com");
        post.getLikeUsers().add(user);

//        System.out.println(post.getLikeUsers());

        // 댓글 쓰기
        Comment comment = new Comment();
        comment.setContent("댓글 111");
        comment.setUser(user);
        comment.setPost(post);
        post.getComments().add(comment);

//        System.out.println(post.getComments() + " " + comment.getPost());

        // 댓글 좋아요 누르기
        comment.getLikeUsers().add(user);
        System.out.println(comment.getLikeUsers());
    }

    @Test
    @Transactional
    void 모든포스트조회() {
        List<Post> posts = postService.readAllPosts();

        for (Post post : posts) {
            System.out.println(post.getId() + " " + post.getUser().getEmail() + " " + post.getTitle() + " " + post.getContent());
        }
    }

    @Test
    @Transactional
    void 특정포스트조회() {
        Long id = 24L;
        Post post = postService.readPost(id);
        System.out.println(post.getId() + " " + post.getUser().getEmail() + " " + post.getTitle() + " " + post.getContent());
    }

    @Test
    @Transactional
    void 포스트수정() {
        PostDTO postDTO = new PostDTO();
        List<String> hashtags = new ArrayList<>();
        hashtags.add("qwe");
        hashtags.add("spring1234");
        postDTO.setTitle("updated title");
        postDTO.setContent("updated content");
        postDTO.setEmail("test@test.com");
        postDTO.setHashtags(hashtags);

        Post post = postService.updatePost(postDTO, 24L);

        System.out.println(post.getId() + " " + post.getUser().getEmail() + " " + post.getTitle() + " " + post.getContent());
        for (Hashtag hashtag : post.getHashtags()) {
            System.out.println(hashtag.getName());
        }
    }

    @Test
    @Transactional
    void 포스트삭제() {
        postService.deletePost(24L);
    }
}
