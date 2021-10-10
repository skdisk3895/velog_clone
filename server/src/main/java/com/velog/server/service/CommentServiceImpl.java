package com.velog.server.service;

import com.velog.server.domain.entity.Comment;
import com.velog.server.domain.entity.User;
import com.velog.server.domain.repository.CommentRepository;
import com.velog.server.domain.repository.PostRepository;
import com.velog.server.domain.repository.UserRepository;
import com.velog.server.dto.comment.CommentInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Comment createComment(CommentInputDTO commentInputDTO, Long postId) {
        System.out.println(commentInputDTO.getContent());
        Comment comment = new Comment();
        comment.setContent(commentInputDTO.getContent());
        comment.setPost(postRepository.findById(postId).get());
        comment.setUser(userRepository.findByEmail(commentInputDTO.getEmail()));

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(CommentInputDTO commentInputDTO, Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.setContent(commentInputDTO.getContent());
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        commentRepository.delete(comment);
    }

    @Transactional
    public void toggleLike(String email, Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        User user = userRepository.findByEmail(email);

        if (comment.getCommentLikeUsers().contains(user)) {
            comment.getCommentLikeUsers().remove(user);
            user.getLikeComments().remove(comment);
        } else {
            comment.getCommentLikeUsers().add(user);
            user.getLikeComments().add(comment);
        }

        commentRepository.save(comment);
        userRepository.save(user);
    }
}
