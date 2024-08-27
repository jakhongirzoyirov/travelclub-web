package com.nomadian.service.logic;

import com.nomadian.service.domain.club.CommunityMember;
import com.nomadian.service.domain.club.Membership;
import com.nomadian.service.domain.club.TravelClub;
import com.nomadian.service.domain.club.vo.RoleInClub;
import com.nomadian.service.MembershipService;
import com.nomadian.controller.dto.MembershipDto;
import com.nomadian.shared.NameValueList;
import com.nomadian.store.ClubStore;
import com.nomadian.store.MemberStore;
import com.nomadian.store.MembershipStore;
import com.nomadian.util.exception.MembershipDuplicationException;
import com.nomadian.util.exception.NoSuchClubException;
import com.nomadian.util.exception.NoSuchMemberException;
import com.nomadian.util.exception.NoSuchMembershipException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceLogic implements MembershipService {
    private MembershipStore membershipStore;
    private ClubStore clubStore;
    private MemberStore memberStore;

    public MembershipServiceLogic(MembershipStore membershipStore, ClubStore clubStore, MemberStore memberStore) {
        this.membershipStore = membershipStore;
        this.memberStore = memberStore;
        this.clubStore = clubStore;
    }

    @Override
    public String registerMembership(MembershipDto membershipDto) {
        String clubId = membershipDto.getClubId();
        String memberEmail = membershipDto.getMemberEmail();
        RoleInClub role = membershipDto.getRole();

        TravelClub club = clubStore.retrieve(clubId);

        if (club == null) {
            throw new NoSuchClubException("No such club with id " + clubId);
        }

        CommunityMember member = memberStore.retrieveByEmail(memberEmail);

        if (member == null) {
            throw new NoSuchMemberException("No such member with id " + memberEmail);
        }

        Membership membership = findMembershipByClubIdAndMemberEmail(clubId, memberEmail);

        if (membership != null) {
            throw new MembershipDuplicationException("Member already exists in the club");
        }

        membership = new Membership(clubId, memberEmail);
        membership.setRole(role);

        String membershipId = membershipStore.create(membership);

        return membershipId;
    }

    @Override
    public Membership findMembership(String membershipId) {
        Membership foundMembership = membershipStore.retrieve(membershipId);
        if (foundMembership == null) {
            throw new NoSuchMembershipException("No such membership with id --> " + membershipId);
        }
        return foundMembership;
    }

    /*@Override
    public Membership findMembershipByClubIdAndMemberId(String clubId, String memberEmail) {
        return membershipStore.retrieve(clubId);
    }*/

    @Override
    public Membership findMembershipByClubIdAndMemberEmail(String clubId, String memberEmail) {
        CommunityMember member = memberStore.retrieveByEmail(memberEmail);

        if (member == null) {
            throw new NoSuchMemberException("No such member with email " + memberEmail);
        }

        Membership foundMembership = membershipStore.retrieveByClubIdAndMemberEmail(clubId, memberEmail);
//        if (foundMembership == null) {
//            throw new NoSuchMembershipException("No such membership with id & email --> " + clubId + "," + memberEmail);
//        }
        return foundMembership;
    }

    @Override
    public List<Membership> findAllMembershipsOfClub(String clubId) {
        List<Membership> foundMemberships = membershipStore.retrieveByClubId(clubId);
        System.out.println("clubId = " + clubId);
        System.out.println("foundMemberships = " + foundMemberships);
        if (foundMemberships == null) {
            throw new NoSuchMembershipException("No such membership with id --> " + clubId);
        }
        return foundMemberships;
    }

    @Override
    public List<Membership> findAllMembershipsOfMember(String memberEmail) {
        List<Membership> foundMemberships = membershipStore.retrieveByMemberEmail(memberEmail);
        if (foundMemberships == null) {
            throw new NoSuchMembershipException("No such membership with email --> " + memberEmail);
        }
        return membershipStore.retrieveByMemberEmail(memberEmail);
    }

    @Override
    public void modifyMembership(String membershipId, NameValueList nameValueList) {
        Membership membership = membershipStore.retrieve(membershipId);

        if (membership == null) {
            throw new NoSuchMembershipException("No such membership");
        }

        membership.modifyValues(nameValueList);

        membershipStore.update(membership);
    }

    @Override
    public void removeMembership(String membershipId) {
        if (membershipStore.exists(membershipId)) {
            throw new NoSuchMembershipException("No such membership with id --> " + membershipId);
        }
        membershipStore.delete(membershipId);
    }
}
