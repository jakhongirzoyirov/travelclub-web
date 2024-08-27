package com.nomadian.service;

import com.nomadian.service.domain.club.TravelClub;
import com.nomadian.controller.dto.TravelClubDto;
import com.nomadian.shared.NameValueList;

import java.util.List;

public interface ClubService {
	String registerClub(TravelClubDto club);
	TravelClub findClubById(String id);
	TravelClub findClubByName(String name);
	List<TravelClub> findAll();
	void modify(String clubId, NameValueList nameValues);
	void remove(String clubId);
}
