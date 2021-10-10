package com.velog.server.service;

import com.velog.server.domain.entity.Hashtag;
import com.velog.server.domain.entity.Post;
import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.HashtagRepositoty;
import com.velog.server.domain.repository.PostRepository;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.dto.post.PostInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private HashtagRepositoty hashtagRepositoty;

    @Transactional
    public List<Post> readAllPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public Post readPost(Long id) {
        Post post = postRepository.findById(id).get();
        return post;
    }

    @Transactional
    public Post createPost(PostInputDTO requestData) {
        Post post = new Post();
        User user = userRepository.findByEmail(requestData.getEmail());
        List<String> hashtags = requestData.getHashtags();

        post.setTitle(requestData.getTitle());
        post.setContent(requestData.getContent());
        post.setUser(user);

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

        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(PostInputDTO requestData, Long id) {
        Post post = postRepository.findById(id).get();
        post.setTitle(requestData.getTitle());
        post.setContent(requestData.getContent());
        post.getHashtags().clear();

        for (String tag : requestData.getHashtags()) {
            Hashtag hashtag = new Hashtag();
            if (hashtagRepositoty.findByName(tag) == null) {
                hashtag.setName(tag);
                post.getHashtags().add(hashtag);
            } else {
                post.getHashtags().add(hashtagRepositoty.findByName(tag));
            }

            hashtag.getPosts().add(post);
        }

        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).get();
        Set<Hashtag> hashtags = post.getHashtags();

        // 연관관계 다 끊기
        for (Hashtag hashtag: post.getHashtags()) {
            hashtag.getPosts().remove(post);
        }
        post.setHashtags(null);

        postRepository.delete(post);

        // check cascade delete
        for (Hashtag hashtag: hashtags) {
            System.out.println(hashtag.getPosts().size());
            if (hashtag.getPosts().size() == 0) {
                hashtagRepositoty.delete(hashtag);
            }
        }
    }

    @Transactional
    public void toggleLike(String email, Long postId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findByEmail(email);


        if (post.getPostLikeUsers().contains(user)) {
            post.getPostLikeUsers().remove(user);
            user.getLikePosts().remove(post);
        } else {
            post.getPostLikeUsers().add(user);
            user.getLikePosts().add(post);
        }

        postRepository.save(post);
    }
}
