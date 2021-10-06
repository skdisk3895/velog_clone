package com.velog.server.dto;

import com.velog.server.domain.entity.Hashtag;
import com.velog.server.domain.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    private String title;
    private String content;
    private Hashtag tags;

    public Post toEntity() {
        return new Post();
    }
}
