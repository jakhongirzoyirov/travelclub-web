package com.nomadian.service;

import com.nomadian.service.domain.club.Membership;
import com.nomadian.controller.dto.MembershipDto;
import com.nomadian.shared.NameValueList;

import java.util.List;

public interface MembershipService {
    String registerMembership(MembershipDto membershipDto);
    Membership findMembership(String membershipId);
    //Membership findMembershipByClubIdAndMemberId(String clubId, String memberId);
    Membership findMembershipByClubIdAndMemberEmail(String clubId, String memberEmail);
    List<Membership> findAllMembershipsOfClub(String clubId);
    List<Membership> findAllMembershipsOfMember(String memberId);
    void modifyMembership(String membershipId, NameValueList nameValueList);
    void removeMembership(String membershipId);
}
