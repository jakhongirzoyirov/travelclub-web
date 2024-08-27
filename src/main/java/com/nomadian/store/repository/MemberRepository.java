package com.nomadian.store.repository;

import com.nomadian.store.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    void deleteByEmail(String email);
    Optional<MemberEntity> findByEmail(String email);
    List<MemberEntity> findByName(String name);
}
