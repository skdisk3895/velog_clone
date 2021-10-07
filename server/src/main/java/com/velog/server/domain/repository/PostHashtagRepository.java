package com.velog.server.domain.repository;

import com.velog.server.domain.entity.PostHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long>{
}
