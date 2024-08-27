package com.nomadian.store.repository;

import com.nomadian.store.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, String> {
    List<BoardEntity> findByName(String name);
    List<BoardEntity> findByClubName(String clubName);
}
