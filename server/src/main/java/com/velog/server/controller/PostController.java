package com.velog.server.controller;

import com.velog.server.domain.entity.Post;
import com.velog.server.dto.PostDTO;
import com.velog.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> ResponseAllPosts() {
        List<Post> posts = postService.readAllPosts();
        List<PostDTO> postDtos = new ArrayList<>();

        for (Post post : posts) {
            Long id = post.getId();
            postDtos.add(post.setToData(post, id));
        }

        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> ResponsePost(@PathVariable Long id) {
        Post post = postService.readPost(id);
        return new ResponseEntity<>(post.setToData(post, id), HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<String> ResponseNewPost(@RequestBody PostDTO postDTO) {
        postService.createPost(postDTO);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> ResponseUpdatePost(@RequestBody PostDTO postDTO, @PathVariable Long id) {
        Post updatedPost = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(updatedPost.setToData(updatedPost, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> ResponseDeletePost(@PathVariable Long id) {
        postService.deletePost(id);
        String message = "success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
