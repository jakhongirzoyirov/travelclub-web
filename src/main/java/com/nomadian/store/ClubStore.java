package com.nomadian.store;

import com.nomadian.service.domain.club.TravelClub;

import java.util.List;

public interface ClubStore {
	String create(TravelClub club);
	TravelClub retrieve(String clubId);
	TravelClub retrieveByName(String name);
	List<TravelClub> retrieveAll();
	void update(TravelClub club);
	void delete(String clubId);
	boolean exists(String clubId);
}
