package com.velog.server.domain.repository;

import com.velog.server.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    Set<Post> findByHashtag_id(Long hashtag_id);
}
