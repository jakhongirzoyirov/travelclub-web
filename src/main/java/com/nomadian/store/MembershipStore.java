package com.nomadian.store;

import com.nomadian.service.domain.club.Membership;

import java.util.List;

public interface MembershipStore {
    String create(Membership membership);
    Membership retrieve(String membershipId);
    Membership retrieveByClubIdAndMemberEmail(String clubId, String memberEmail);
    List<Membership> retrieveByClubId(String clubId);
    List<Membership> retrieveByMemberEmail(String memberEmail);
    void update(Membership membership);
    void delete(String membershipId);
    boolean exists(String membershipId);
}
