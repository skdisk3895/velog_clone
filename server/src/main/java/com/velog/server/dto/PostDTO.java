package com.velog.server.dto;

import com.velog.server.domain.entity.Hashtag;
import com.velog.server.domain.entity.Post;
import com.velog.server.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDTO {

    private String title;
    private String content;
//    private List<String> tags;
    private User user;

    public Post toEntity() {
        return new Post(null, title, content, user);
    }
}
