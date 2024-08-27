package com.nomadian.service.logic;

import com.nomadian.service.domain.club.CommunityMember;
import com.nomadian.service.MemberService;
import com.nomadian.controller.dto.LoginDto;
import com.nomadian.controller.dto.MemberDto;
import com.nomadian.shared.NameValueList;
import com.nomadian.store.MemberStore;
import com.nomadian.util.exception.MemberDuplicationException;
import com.nomadian.util.exception.NoSuchMemberException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceLogic implements MemberService {
	private MemberStore memberStore;

	public MemberServiceLogic(MemberStore memberStore) {
		this.memberStore = memberStore;
	}

	@Override
	public String registerMember(MemberDto newMemberDto) {
		String email = newMemberDto.getEmail();
		CommunityMember foundedmember = memberStore.retrieveByEmail(email);
		System.out.println("foundedmember = " + foundedmember);
		if (foundedmember != null) {
			System.out.println(foundedmember != null);
			throw new MemberDuplicationException("Member already exists with email: " + foundedmember.getEmail());
		}

		foundedmember = new CommunityMember(
				newMemberDto.getEmail(),
				newMemberDto.getPassword(),
				newMemberDto.getName(),
				newMemberDto.getNickName(),
				newMemberDto.getPhoneNumber(),
				newMemberDto.getBirthDay()
		);
		foundedmember.setNickName(newMemberDto.getNickName());
		foundedmember.setBirthDay(newMemberDto.getBirthDay());

//		foundedmember.checkValidation();

		System.out.println("foundedmember = " + foundedmember);
		System.out.println("memberStore.create(foundedmember) = " + memberStore.create(foundedmember));

		return foundedmember.getEmail();
	}

	@Override
	public void emailDuplication(String email) {
		CommunityMember member = new CommunityMember();
//		member.checkEmailValidation(email);
		if (memberStore.retrieveByEmail(email) != null) {
			throw new MemberDuplicationException("Member already exist with email " + email);
		}
	}

	@Override
	public CommunityMember login(LoginDto loginDto) {
		CommunityMember member = new CommunityMember();
//		member.checkEmailValidation(loginDto.getEmail());
		member = findMemberByEmail(loginDto.getEmail());
		if (!member.getPassword().equals(loginDto.getPassword())) {
			throw new IllegalArgumentException("Wrong Password");
		}
		return member;
	}

	@Override
	public CommunityMember findMemberByEmail(String email) {
		CommunityMember foundMembers = memberStore.retrieveByEmail(email);
		if (foundMembers == null) {
			throw new NoSuchMemberException("No such Member with email --> " + email);
		}
		return foundMembers;
	}

	@Override
	public List<CommunityMember> findMembersByName(String name) {
		List<CommunityMember> foundMembers = memberStore.retrieveByName(name);
		if (foundMembers == null) {
			throw new NoSuchMemberException("No such Member with name --> " + name);
		}
		return foundMembers;
	}

	@Override
	public void modifyMember(String email, NameValueList nameValueList) {
		CommunityMember targetMember = memberStore.retrieveByEmail(email);

		if (targetMember == null) {
			throw new NoSuchMemberException("No such member with id " + email);
		}

		targetMember.modifyValues(nameValueList);

		memberStore.update(targetMember);
	}

	@Override
	public void removeMember(String email) {
		if (!memberStore.exists(email)) {
			throw new NoSuchMemberException("No such member with id " + email);
		}

		memberStore.delete(email);
	}
}
