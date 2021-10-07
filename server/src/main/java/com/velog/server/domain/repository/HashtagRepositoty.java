package com.velog.server.domain.repository;

import com.velog.server.domain.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepositoty extends JpaRepository<Hashtag, Long> {
    Hashtag findByName(String name);
}
