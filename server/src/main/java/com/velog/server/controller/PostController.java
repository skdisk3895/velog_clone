package com.velog.server.controller;

import com.velog.server.domain.entity.Post;
import com.velog.server.dto.auth.UserDTO;
import com.velog.server.dto.post.PostInputDTO;
import com.velog.server.dto.post.PostOutputDTO;
import com.velog.server.model.response.CommonResult;
import com.velog.server.model.response.ListResult;
import com.velog.server.model.response.SingleResult;
import com.velog.server.service.PostService;
import com.velog.server.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = {"2. Post"})
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private ResponseService responseService;

    @ApiOperation(value = "모든 포스트 조회", notes = "모든 포스트를 조회한다.")
    @GetMapping("/all")
    public ListResult<PostOutputDTO> ResponseAllPosts() {
        List<Post> posts = postService.readAllPosts();
        List<PostOutputDTO> ResponseDatas = new ArrayList<>();
        for (Post post: posts) {
            PostOutputDTO postOutputDTO = new PostOutputDTO();
            ResponseDatas.add(postOutputDTO.setToData(post));
        }
        return responseService.getListResult(ResponseDatas);
    }

    @ApiOperation(value = "특정 포스트 조회", notes = "특정 포스트를 조회한다.")
    @GetMapping("/{id}")
    public SingleResult<PostOutputDTO> ResponsePost(@PathVariable @ApiParam(value = "조회할 포스트 id값", required = true) Long id) {
        Post post = postService.readPost(id);
        PostOutputDTO responseData = new PostOutputDTO();
        return responseService.getSingleResult(responseData.setToData(post));
    }

    @ApiOperation(value = "포스트 작성", notes = "포스트를 작성한다.")
    @PostMapping("/write")
    public SingleResult<PostOutputDTO> ResponseNewPost(@RequestBody @ApiParam(value = "포스트 폼 객체", required = true) PostInputDTO postInputDTO) {
        PostOutputDTO responseData = new PostOutputDTO();
        return responseService.getSingleResult(responseData.setToData(postService.createPost(postInputDTO)));
    }

    @ApiOperation(value = "포스트 수정", notes = "특정 포스트를 수정한다.")
    @PutMapping("/update/{id}")
    public SingleResult<PostOutputDTO> ResponseModifiedPost(@RequestBody @ApiParam(value = "특정 포스트 폼 객체", required = true) PostInputDTO requestData,
                                                       @PathVariable @ApiParam(value = "특정 포스트 id값", required = true) Long id) {
        Post updatedPost = postService.updatePost(requestData, id);
        PostOutputDTO responseData = new PostOutputDTO();
        return responseService.getSingleResult(responseData.setToData(updatedPost));
    }

    @ApiOperation(value = "포스트 삭제", notes = "특정 포스트를 삭제한다.")
    @DeleteMapping("/delete/{id}")
    public CommonResult ResponseDeletePost(@PathVariable @ApiParam(value = "특정 포스트 id값", required = true) Long id) {
        postService.deletePost(id);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "포스트 좋아요", notes = "특정 포스트에 좋아요를 누른다.")
    @PostMapping("/like/{postId}")
    public CommonResult ResponseLikePost(@RequestBody @ApiParam(value = "유저 정보", required = true) UserDTO userData,
                                         @PathVariable("postId") @ApiParam(value = "특정 포스트 id값", required = true) Long postId) {
        postService.toggleLike(userData.getEmail(), postId);
        return responseService.getSuccessResult();
    }
}
