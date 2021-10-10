package com.velog.server.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostInputDTO {
    private String title;
    private String content;
    private String email;
    private List<String> hashtags;
}
