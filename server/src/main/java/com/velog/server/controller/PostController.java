package com.velog.server.controller;

import com.velog.server.domain.entity.Post;
import com.velog.server.dto.PostDTO;
import com.velog.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/all")
    public List<Post> ResponseAllPosts() {
        return postService.readAllPosts();
    }

    @PostMapping("/write")
    public void ResponseNewPost(@RequestBody PostDTO postDTO) {
        postService.createPost(postDTO);
    }

    @PutMapping("/update/{id}")
    public void ResponseUpdatePost(@RequestBody PostDTO postDTO, @PathVariable Long id) {
        postService.updatePost(postDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    public void ResponseDeletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
