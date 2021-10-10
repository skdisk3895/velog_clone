package com.velog.server.dto.comment;

import com.velog.server.domain.entity.Comment;
import com.velog.server.domain.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentOutputDTO {

    private String content;
    private String email;
    private Post post;

    public CommentOutputDTO setToData(Comment comment) {
        CommentOutputDTO commentOutputDTO = new CommentOutputDTO();

        commentOutputDTO.setContent(comment.getContent());
        commentOutputDTO.setEmail(comment.getUser().getEmail());
        commentOutputDTO.setPost(comment.getPost());

        return commentOutputDTO;
    }
}
