package com.nomadian.store.repository;

import com.nomadian.store.entity.PostingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<PostingEntity, String> {
    List<PostingEntity> findByBoardId(String boardId);
    List<PostingEntity> findByTitle(String title);
}
