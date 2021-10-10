package com.velog.server.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentInputDTO {

    private String content;
    private String email;
}
