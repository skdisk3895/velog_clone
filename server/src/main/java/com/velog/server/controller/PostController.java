package com.velog.server.controller;

import com.velog.server.domain.entity.Post;
import com.velog.server.dto.PostDTO;
import com.velog.server.dto.UserDTO;
import com.velog.server.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = {"2. Post"})
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @ApiOperation(value = "모든 포스트 조회", notes = "모든 포스트를 조회한다.")
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

    @ApiOperation(value = "특정 포스트 조회", notes = "특정 포스트를 조회한다.")
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> ResponsePost(@PathVariable @ApiParam(value = "조회할 포스트 id값", required = true) Long id) {
        Post post = postService.readPost(id);
        return new ResponseEntity<>(post.setToData(post, id), HttpStatus.OK);
    }

    @ApiOperation(value = "포스트 작성", notes = "포스트를 작성한다.")
    @PostMapping("/write")
    public ResponseEntity<String> ResponseNewPost(@RequestBody @ApiParam(value = "포스트 폼 객체", required = true) PostDTO postDTO) {
        postService.createPost(postDTO);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @ApiOperation(value = "포스트 수정", notes = "특정 포스트를 수정한다.")
    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> ResponseUpdatePost(@RequestBody @ApiParam(value = "특정 포스트 폼 객체", required = true) PostDTO postDTO,
                                                      @PathVariable @ApiParam(value = "특정 포스트 id값", required = true) Long id) {
        Post updatedPost = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(updatedPost.setToData(updatedPost, id), HttpStatus.OK);
    }

    @ApiOperation(value = "포스트 삭제", notes = "특정 포스트를 삭제한다.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> ResponseDeletePost(@PathVariable @ApiParam(value = "특정 포스트 id값", required = true) Long id) {
        postService.deletePost(id);
        String message = "success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ApiOperation(value = "포스트 좋아요", notes = "특정 포스트에 좋아요를 누른다.")
    @PostMapping("/like/{postId}")
    public ResponseEntity<String> ResponseLikePost(@RequestBody @ApiParam(value = "유저 정보", required = true) UserDTO userDTO,
                                                   @PathVariable("postId") @ApiParam(value = "특정 포스트 id값", required = true) Long postId) {
        postService.toggleLike(userDTO.getEmail(), postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
