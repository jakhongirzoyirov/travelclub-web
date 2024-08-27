package com.nomadian.store.repository;

import com.nomadian.store.entity.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<ClubEntity, Long> {
    List<ClubEntity> findAllByName(String name);
    ClubEntity findByName(String name);
}
