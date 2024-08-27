package com.nomadian.controller;

import com.nomadian.service.domain.club.Membership;
import com.nomadian.service.MembershipService;
import com.nomadian.controller.dto.MembershipDto;
import com.nomadian.shared.NameValueList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memberships")
public class MembershipController {
    private MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping
    public String register(@RequestBody @Valid MembershipDto membershipDto) {
        return membershipService.registerMembership(membershipDto);
    }

    @GetMapping("/{membershipId}")
    public Membership find(@PathVariable String membershipId) {
        return membershipService.findMembership(membershipId);
    }

    /*@GetMapping("/club/{clubId}/member/{memberId}")
    public Membership findMembershipByClubIdAndMemberId(@PathVariable String clubId, @PathVariable String memberId) {
        System.out.println(clubId);
        System.out.println(memberId);
        return membershipService.findMembershipByClubIdAndMemberId(clubId, memberId);
    }*/

    @GetMapping("/club/{clubId}/email/{memberEmail}")
    public Membership findMembershipByClubIdAndMemberEmail(@PathVariable String clubId, @PathVariable String memberEmail) {
        System.out.println(clubId);
        System.out.println(memberEmail);
        return membershipService.findMembershipByClubIdAndMemberEmail(clubId, memberEmail);
    }

    @GetMapping("/club/{clubId}")
    public List<Membership> findAllMembershipsOfClub(@PathVariable String clubId) {
        return membershipService.findAllMembershipsOfClub(clubId);
    }

    @GetMapping("/member/{memberEmail}")
    public List<Membership> findAllMembershipsOfMember(@PathVariable String memberEmail) {
        return membershipService.findAllMembershipsOfMember(memberEmail);
    }

    @PutMapping("/{membershipId}")
    public void modify(@PathVariable String membershipId, @RequestBody NameValueList nameValueList) {
        membershipService.modifyMembership(membershipId, nameValueList);
    }

    @DeleteMapping("/{membershipId}")
    public void remove(@PathVariable String membershipId) {
        membershipService.removeMembership(membershipId);
    }
}
