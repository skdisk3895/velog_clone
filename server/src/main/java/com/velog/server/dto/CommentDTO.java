package com.velog.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private Long comment_id;
    private String content;
    private String email;
}
