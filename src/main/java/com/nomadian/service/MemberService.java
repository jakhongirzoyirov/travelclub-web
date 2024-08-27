package com.nomadian.service;

import com.nomadian.service.domain.club.CommunityMember;
import com.nomadian.controller.dto.LoginDto;
import com.nomadian.controller.dto.MemberDto;
import com.nomadian.shared.NameValueList;

import java.util.List;

public interface MemberService {
	String registerMember(MemberDto member);
	void emailDuplication(String memberEmail);
	CommunityMember login(LoginDto loginDto);
	CommunityMember findMemberByEmail(String memberEmail);
	List<CommunityMember> findMembersByName(String name);
	void modifyMember(String memberEmail, NameValueList member);
	void removeMember(String memberEmail);
}