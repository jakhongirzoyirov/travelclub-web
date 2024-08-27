package com.nomadian.store.repository;

import com.nomadian.store.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<MembershipEntity, String> {
    MembershipEntity findByTravelClubIdAndMemberEmail(Long clubId, String memberEmail);
    List<MembershipEntity> findByTravelClubId(Long clubId);
    List<MembershipEntity> findByMemberEmail(String memberEmail);
}
