package com.velog.server.service;

import com.velog.server.domain.entity.Comment;
import com.velog.server.dto.comment.CommentInputDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public Comment createComment(CommentInputDTO commentInputDTO, Long postId);
    public Comment updateComment(CommentInputDTO commentInputDTO, Long commentId);
    public void deleteComment(Long commentId);
    public void toggleLike(String email, Long commentId);
}
