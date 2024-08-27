package com.nomadian.store.impl;

import com.nomadian.service.domain.club.CommunityMember;
import com.nomadian.store.MemberStore;
import com.nomadian.store.entity.MemberEntity;
import com.nomadian.store.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemberJpaStore implements MemberStore {

    private MemberRepository memberRepository;

    public MemberJpaStore(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String create(CommunityMember member) {
        System.out.println("memberRepository.save(new MemberEntity(member)) = " + memberRepository.save(member.toEntity()));
        return member.getEmail();
    }

    /*@Override
    public CommunityMember retrieve(String memberId) {
        Optional<MemberJpo> memberJpo = memberRepository.findById(memberId);
        return memberJpo.map(MemberJpo::toDomain).orElse(null);
    }*/

    @Override
    public CommunityMember retrieveByEmail(String email) {
        Optional<MemberEntity> memberJpo = memberRepository.findByEmail(email);
        return memberJpo.map(MemberEntity::toDomain).orElse(null);
    }

    @Override
    public List<CommunityMember> retrieveByName(String name) {
        List<MemberEntity> memberEntities = memberRepository.findByName(name);
        return memberEntities.stream().map(MemberEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(CommunityMember member) {
        memberRepository.save(member.toEntity());
    }

    @Transactional
    @Override
    public void delete(String email) {
        memberRepository.deleteByEmail(email);
    }

    @Override
    public boolean exists(String memberId) {
        return memberRepository.existsById(memberId);
    }
}
