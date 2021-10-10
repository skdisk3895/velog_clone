package com.velog.server.service;

import com.velog.server.domain.entity.Post;
import com.velog.server.dto.post.PostInputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    public List<Post> readAllPosts();
    public Post readPost(Long id);
    public Post createPost(PostInputDTO requestData);
    public Post updatePost(PostInputDTO requestData, Long id);
    public void deletePost(Long id);
    public void toggleLike(String email, Long postId);
}
