package com.velog.server.controller;

import com.velog.server.domain.entity.Comment;
import com.velog.server.domain.entity.Post;
import com.velog.server.dto.CommentDTO;
import com.velog.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/write")
    public ResponseEntity<Comment> responseCreateComment(@RequestBody CommentDTO commentDTO, @PathVariable("postId") Long postId) {
        return new ResponseEntity<>(commentService.createComment(commentDTO, postId), HttpStatus.CREATED);
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<Comment> responseUpdateComment(@RequestBody CommentDTO commentDTO, @PathVariable("commentId") Long commentId) {
        Comment comment = commentService.updateComment(commentDTO, commentId);
        Post post = comment.getPost();
        return new ResponseEntity<>(commentService.updateComment(commentDTO, commentId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> responseDeleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/like/{commentId}")
    public ResponseEntity<String> ResponseLikeComment(String email, @PathVariable("commentId") Long commentId) {
        commentService.toggleLike(email, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
