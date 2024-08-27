package com.nomadian.store.impl;

import com.nomadian.service.domain.club.Membership;
import com.nomadian.service.domain.club.TravelClub;
import com.nomadian.store.MembershipStore;
import com.nomadian.store.entity.ClubEntity;
import com.nomadian.store.entity.MembershipEntity;
import com.nomadian.store.repository.ClubRepository;
import com.nomadian.store.repository.MembershipRepository;
import com.nomadian.util.exception.NoSuchClubException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MembershipJpaStore implements MembershipStore {

    private MembershipRepository membershipRepository;
    private ClubRepository clubRepository;

    public MembershipJpaStore(MembershipRepository membershipRepository, ClubRepository clubRepository) {
        this.membershipRepository = membershipRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public String create(Membership membership) {
        System.out.println("membership = " + membership);
        TravelClub travelClub = clubRepository.findById(Long.valueOf(membership.getClubId()))
                .map(ClubEntity::toDomain)
                .orElseThrow(() -> new NoSuchClubException(""));
        System.out.println("travelClub = " + travelClub);
        MembershipEntity membershipEntity = membership.toEntity(travelClub);
        System.out.println("membershipEntity = " + membershipEntity);
        membershipRepository.save(membershipEntity);

        return membership.getClubId();
    }

    @Override
    public Membership retrieve(String membershipId) {
        Optional<MembershipEntity> membershipJpo = membershipRepository.findById(membershipId);

        return membershipJpo.stream().map(MembershipEntity::toDomain).findAny().orElse(null);
    }

    @Override
    public Membership retrieveByClubIdAndMemberEmail(String clubId, String memberEmail) {
        Optional<MembershipEntity> membershipJpo = Optional.ofNullable(membershipRepository.findByTravelClubIdAndMemberEmail(Long.valueOf(clubId), memberEmail));
        System.out.println("membershipJpo = " + membershipJpo);
        return membershipJpo.map(MembershipEntity::toDomain).orElse(null);
    }

    @Override
    public List<Membership> retrieveByClubId(String clubId) {
        List<MembershipEntity> membershipEntities = membershipRepository.findByTravelClubId(Long.valueOf(clubId));
        System.out.println("membershipEntities = " + membershipEntities);

        return membershipEntities.stream().map(MembershipEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Membership> retrieveByMemberEmail(String memberEmail) {
        List<MembershipEntity> membershipEntities = membershipRepository.findByMemberEmail(memberEmail);

        return membershipEntities.stream().map(MembershipEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(Membership membership) {
        TravelClub travelClub = clubRepository.findById(Long.valueOf(membership.getClubId()))
                .map(clubEntity -> clubEntity.toDomain())
                .orElseThrow(() -> new NoSuchClubException(""));

        membershipRepository.save(membership.toEntity(travelClub));
    }

    @Override
    public void delete(String membershipId) {
        membershipRepository.deleteById(membershipId);
    }

    @Override
    public boolean exists(String membershipId) {
        return membershipRepository.existsById(membershipId);
    }
}
