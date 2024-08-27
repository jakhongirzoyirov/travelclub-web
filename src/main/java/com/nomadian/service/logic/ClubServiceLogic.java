package com.nomadian.service.logic;

import com.nomadian.service.domain.club.TravelClub;
import com.nomadian.service.ClubService;
import com.nomadian.controller.dto.TravelClubDto;
import com.nomadian.shared.NameValueList;
import com.nomadian.store.ClubStore;
import com.nomadian.util.exception.ClubDuplicationException;
import com.nomadian.util.exception.NoSuchClubException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("clubService")
public class ClubServiceLogic implements ClubService {
	private ClubStore clubStore;

	public ClubServiceLogic(ClubStore clubStore) {

		this.clubStore = clubStore;
	}

	@Override
	public String registerClub(TravelClubDto clubCdo) {
		System.out.println(clubCdo);
		TravelClub club = new TravelClub(clubCdo.getName(), clubCdo.getIntro());
//		club.checkValidation();


		Optional.ofNullable(clubStore.retrieveByName(club.getName()))
				.ifPresent(c -> {
					throw new ClubDuplicationException("Club already exist with name --> " + c.getName());
				});


		String clubId = clubStore.create(club);
		return clubId;
	}

	@Override
	public TravelClub findClubById(String id) {
		TravelClub foundClub = clubStore.retrieve(id);
		if (foundClub == null) {
			throw new NoSuchClubException("No such club with id --> " + id);
		}
		System.out.println("foundClub = " + foundClub);
		return foundClub;
	}

	@Override
	public TravelClub findClubByName(String name) {
		TravelClub foundClub = clubStore.retrieveByName(name);
		if (foundClub == null) {
			throw new NoSuchClubException("No such club with name --> " + name);
		}
		return foundClub;
	}

	@Override
	public List<TravelClub> findAll(){
		List<TravelClub> foundClubs = clubStore.retrieveAll();
		if (foundClubs == null) {
			throw new NoSuchClubException("No such clubs");
		}
		return foundClubs;
	}

	@Override
	public void modify(String clubId, NameValueList nameValueList) {
		TravelClub foundedClub = clubStore.retrieve(clubId);
		if (foundedClub == null) {
			throw new NoSuchClubException("No such club with id " + clubId);
		}
		foundedClub.modifyValues(nameValueList);
		clubStore.update(foundedClub);
	}

	@Override
	public void remove(String clubId) {
		if (!clubStore.exists(clubId)) {
			throw new NoSuchClubException("No such club with id " + clubId);
		}
		clubStore.delete(clubId);
	}
}
