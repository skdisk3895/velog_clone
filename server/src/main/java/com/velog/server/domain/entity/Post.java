package com.velog.server.domain.entity;

import com.velog.server.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "post_hashtag",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "hashtag_id")})
    private Set<Hashtag> hashtags = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "post_like_user",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> likeUsers = new HashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PostDTO setToData(Post post, Long id) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPost_id(id);
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setEmail(post.getUser().getEmail());

        List<String> tags = new ArrayList<>();
        for (Hashtag hashtag: post.getHashtags()) {
            tags.add(hashtag.getName());
        }
        postDTO.setHashtags(tags);

        return postDTO;
    }
}
