package com.nomadian.store.impl;

import com.nomadian.service.domain.club.TravelClub;
import com.nomadian.store.ClubStore;
import com.nomadian.store.entity.ClubEntity;
import com.nomadian.store.repository.ClubRepository;
import com.nomadian.util.exception.NoSuchClubException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClubJpaStore implements ClubStore {

    private ClubRepository clubRepository;

    public ClubJpaStore(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public String create(TravelClub club) {
        ClubEntity clubEntity = club.toEntity();
        System.out.println(club);
        System.out.println(clubEntity);

//        List<TravelClub> travelClubs = retrieveAll();
//        club.setAutoId(String.format("%05d", travelClubs.size()+1));
//        clubEntity.setId(club.getId());
        clubRepository.save(clubEntity);
        return clubEntity.getId().toString();
    }

    @Override
    public TravelClub retrieve(String clubId) {
        Optional<ClubEntity> clubJpo = clubRepository.findById(Long.valueOf(clubId));
        System.out.println(clubJpo);

        return clubJpo.map(ClubEntity::toDomain).orElse(null);
    }

    @Override
    public TravelClub retrieveByName(String name) {
        ClubEntity clubEntity = clubRepository.findByName(name);
        if (clubEntity == null){
            return null;
        }
        return clubEntity.toDomain();
    }

    @Override
    public List<TravelClub> retrieveAll() {
        List<ClubEntity> clubEntities = clubRepository.findAll();
        // return clubJpos.stream().map(clubJpo -> clubJpo.toDomain()).collect(Collectors.toList());
        return clubEntities.stream().map(ClubEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(TravelClub club) {
        System.out.println("club = " + club);
        delete(club.getId());
        clubRepository.save(club.toEntity());
    }

//    @Transactional
    @Override
    public void delete(String clubId) {
        clubRepository.deleteById(Long.valueOf(clubId));
    }

    @Override
    public boolean exists(String clubId) {
        return clubRepository.existsById(Long.valueOf(clubId));
    }
}
