package com.velog.server.service;

import com.velog.server.domain.entity.Post;
import com.velog.server.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    public List<Post> readAllPosts();
    public void createPost(PostDTO postDTO);
    public void updatePost(PostDTO postDTO, Long id);
    public void deletePost(Long id);
}
