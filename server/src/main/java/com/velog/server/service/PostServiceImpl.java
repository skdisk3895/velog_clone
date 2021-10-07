package com.velog.server.service;

import com.velog.server.domain.entity.Post;
import com.velog.server.domain.repository.PostRepository;
import com.velog.server.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> readAllPosts() {
        return postRepository.findAll();
    }

    public void createPost(PostDTO postDTO) {
//        postRepository.save(postDTO.toEntity());
    }

    public void updatePost(PostDTO postDTO, Long id) {
        Post post = postRepository.findById(id).get();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id).get();
        postRepository.delete(post);
    }
}
