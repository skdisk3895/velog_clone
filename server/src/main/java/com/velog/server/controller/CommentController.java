package com.velog.server.controller;

import com.velog.server.domain.entity.Comment;
import com.velog.server.domain.entity.Post;
import com.velog.server.dto.CommentDTO;
import com.velog.server.dto.UserDTO;
import com.velog.server.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"3. Comment"})
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성한다.")
    @PostMapping("/{postId}/write")
    public ResponseEntity<Comment> responseCreateComment(@RequestBody @ApiParam(value = "댓글 폼 객체", required = true) CommentDTO commentDTO,
                                                         @PathVariable("postId") @ApiParam(value = "특정 포스트 id값", required = true) Long postId) {
        return new ResponseEntity<>(commentService.createComment(commentDTO, postId), HttpStatus.CREATED);
    }

    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.")
    @PutMapping("/update/{commentId}")
    public ResponseEntity<Comment> responseUpdateComment(@RequestBody @ApiParam(value = "댓글 폼 객체", required = true)  CommentDTO commentDTO,
                                                         @PathVariable("commentId") @ApiParam(value = "특정 댓글 id값", required = true) Long commentId) {
        Comment comment = commentService.updateComment(commentDTO, commentId);
        Post post = comment.getPost();
        return new ResponseEntity<>(commentService.updateComment(commentDTO, commentId), HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.")
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> responseDeleteComment(@PathVariable("commentId") @ApiParam(value = "특정 댓글 id값", required = true) Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 좋아요", notes = "특정 댓글에 좋아요를 누른다.")
    @PostMapping("/like/{commentId}")
    public ResponseEntity<String> ResponseLikeComment(@RequestBody @ApiParam(value = "유저 정보", required = true) UserDTO userDTO,
                                                      @PathVariable("commentId") @ApiParam(value = "특정 댓글 id값", required = true) Long commentId) {
        commentService.toggleLike(userDTO.getEmail(), commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
