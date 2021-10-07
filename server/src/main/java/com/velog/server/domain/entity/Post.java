package com.velog.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "post_hashtag",
//        joinColumns = {@JoinColumn(name = "post_id")},
//        inverseJoinColumns = {@JoinColumn(name = "hashtag_id")})
    @OneToMany(mappedBy = "post")
    private Set<PostHashtag> postHashtags = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
