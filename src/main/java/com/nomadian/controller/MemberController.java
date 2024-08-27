package com.nomadian.controller;

import com.nomadian.service.domain.club.CommunityMember;
import com.nomadian.service.MemberService;
import com.nomadian.controller.dto.LoginDto;
import com.nomadian.controller.dto.MemberDto;
import com.nomadian.shared.NameValueList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public String register(@RequestBody @Valid MemberDto memberDto) {

        return memberService.registerMember(memberDto);
    }

    @PostMapping("/login")
    public CommunityMember login(@RequestBody @Valid LoginDto loginDto) {
        return memberService.login(loginDto);
    }

    @GetMapping("/{memberEmail}")
    public CommunityMember findByEmail(@PathVariable String memberEmail) {
        return memberService.findMemberByEmail(memberEmail);
    }

    /*@GetMapping("/{memberId}")
    public CommunityMember findById(@PathVariable String memberId) {
        return memberService.findMemberById(memberId);
    }*/

    @GetMapping("/{name}")
    public List<CommunityMember> findByName(@PathVariable String name) {
        return memberService.findMembersByName(name);
    }

    @PutMapping("/{memberEmail}")
    public void modify(@PathVariable String memberEmail, @RequestBody NameValueList nameValueList) {
        memberService.modifyMember(memberEmail, nameValueList);
    }

    @DeleteMapping("/{memberEmail}")
    public void delete(@PathVariable String memberEmail) {
        memberService.removeMember(memberEmail);
    }
}
