package com.velog.server.dto.post;

import com.velog.server.domain.entity.Comment;
import com.velog.server.domain.entity.Hashtag;
import com.velog.server.domain.entity.Post;
import com.velog.server.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PostOutputDTO {
    private Long post_id;
    private String title;
    private String content;
    private String email;
    private List<Hashtag> hashtags;
    private List<Comment> comments;
    private List<User> likeUsers;

    public PostOutputDTO setToData(Post post) {
        PostOutputDTO postOutputDTO = new PostOutputDTO();
        postOutputDTO.setPost_id(post.getId());
        postOutputDTO.setTitle(post.getTitle());
        postOutputDTO.setContent(post.getContent());
        postOutputDTO.setEmail(post.getUser().getEmail());

        List<Hashtag> hashtags = new ArrayList<>();
        for (Hashtag hashtag: post.getHashtags()) {
            hashtags.add(hashtag);
        }
        postOutputDTO.setHashtags(hashtags);

        List<Comment> comments = new ArrayList<>();
        for (Comment comment: post.getComments()) {
            comments.add(comment);
        }
        postOutputDTO.setComments(comments);

        List<User> likeUsers = new ArrayList<>();
        for (User user : post.getPostLikeUsers()) {
            likeUsers.add(user);
        }
        postOutputDTO.setLikeUsers(likeUsers);

        return postOutputDTO;
    }
}
