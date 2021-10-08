package com.velog.server.dto;

import com.velog.server.domain.entity.Hashtag;
import com.velog.server.domain.entity.Post;
//import com.velog.server.domain.entity.PostHashtag;
import com.velog.server.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDTO {

    private Long post_id;
    private String title;
    private String content;
    private List<String> hashtags;
    private String email;

//    public Post toEntity() {
//        return new Post(null, title, content, user);
//    }
}
