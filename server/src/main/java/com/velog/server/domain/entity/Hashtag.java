package com.velog.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hashtag")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "hashtags")
    @OneToMany(mappedBy = "hashtag")
    private Set<PostHashtag> postHashtags = new HashSet<>();
}
