package com.velog.server.service;

import com.velog.server.domain.entity.Hashtag;
import com.velog.server.domain.entity.Post;
import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.HashtagRepositoty;
import com.velog.server.domain.repository.PostRepository;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.dto.PostDTO;
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
        return postRepository.findById(id).get();
    }

    @Transactional
    public void createPost(PostDTO postDTO) {
        Post post = new Post();
        User user = userRepository.findByEmail(postDTO.getEmail());
        List<String> hashtags = postDTO.getHashtags();

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
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

        postRepository.save(post);
    }

    @Transactional
    public Post updatePost(PostDTO postDTO, Long id) {
        Post post = postRepository.findById(id).get();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.getHashtags().clear();

        for (String tag : postDTO.getHashtags()) {
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

        postRepository.delete(post);

        for (Hashtag hashtag: hashtags) {
            System.out.println(hashtag.getPosts().size());
            if (hashtag.getPosts().size() == 1) {
                hashtagRepositoty.delete(hashtag);
            }
        }
    }
}
