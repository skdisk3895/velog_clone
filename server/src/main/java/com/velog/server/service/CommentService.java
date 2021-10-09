package com.velog.server.service;

import com.velog.server.domain.entity.Comment;
import com.velog.server.dto.CommentDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public Comment createComment(CommentDTO commentDTO, Long postId);
    public Comment updateComment(CommentDTO commentDTO, Long commentId);
    public void deleteComment(Long commentId);
    public void toggleLike(String email, Long commentId);
}
