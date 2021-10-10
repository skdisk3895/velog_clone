package com.velog.server.controller;

import com.velog.server.domain.entity.Comment;
import com.velog.server.dto.comment.CommentInputDTO;
import com.velog.server.dto.auth.UserDTO;
import com.velog.server.dto.comment.CommentOutputDTO;
import com.velog.server.model.response.CommonResult;
import com.velog.server.model.response.SingleResult;
import com.velog.server.service.CommentService;
import com.velog.server.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"3. Comment"})
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ResponseService responseService;

    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성한다.")
    @PostMapping("/{postId}/write")
    public SingleResult<CommentOutputDTO> responseCreateComment(@RequestBody @ApiParam(value = "댓글 폼 객체", required = true) CommentInputDTO requestData,
                                                       @PathVariable("postId") @ApiParam(value = "특정 포스트 id값", required = true) Long postId) {
        Comment comment = commentService.createComment(requestData, postId);
        CommentOutputDTO responseData = new CommentOutputDTO();
        return responseService.getSingleResult(responseData.setToData(comment));
    }

    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.")
    @PutMapping("/update/{commentId}")
    public SingleResult<CommentOutputDTO> responseUpdateComment(@RequestBody @ApiParam(value = "댓글 폼 객체", required = true) CommentInputDTO requestData,
                                                         @PathVariable("commentId") @ApiParam(value = "특정 댓글 id값", required = true) Long commentId) {
        Comment modifiedComment = commentService.updateComment(requestData, commentId);
        CommentOutputDTO responseData = new CommentOutputDTO();
        return responseService.getSingleResult(responseData.setToData(modifiedComment));
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.")
    @DeleteMapping("/delete/{commentId}")
    public CommonResult responseDeleteComment(@PathVariable("commentId") @ApiParam(value = "특정 댓글 id값", required = true) Long commentId) {
        commentService.deleteComment(commentId);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "댓글 좋아요", notes = "특정 댓글에 좋아요를 누른다.")
    @PostMapping("/like/{commentId}")
    public CommonResult ResponseLikeComment(@RequestBody @ApiParam(value = "유저 정보", required = true) UserDTO requestData,
                                                      @PathVariable("commentId") @ApiParam(value = "특정 댓글 id값", required = true) Long commentId) {
        commentService.toggleLike(requestData.getEmail(), commentId);
        return responseService.getSuccessResult();
    }
}
