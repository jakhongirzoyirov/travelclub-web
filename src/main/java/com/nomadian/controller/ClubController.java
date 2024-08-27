package com.nomadian.controller;


import com.nomadian.service.domain.club.TravelClub;
import com.nomadian.service.ClubService;
import com.nomadian.controller.dto.TravelClubDto;
import com.nomadian.shared.NameValueList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping// localhost:8080/club
    public String register(@RequestBody @Valid TravelClubDto clubCdo) {

        return clubService.registerClub(clubCdo);
    }

    @GetMapping
    public List<TravelClub> findAll() {
        return clubService.findAll();
    }

    @GetMapping("/{clubId}")
    public TravelClub find(@PathVariable String clubId) {
        return clubService.findClubById(clubId);
    }

    @GetMapping   // localhost:8080/club?name=클럽이름
    public TravelClub findByName(@RequestParam String name) {
        return clubService.findClubByName(name);
    }

    @PutMapping("/{clubId}")
    public void modify(@PathVariable String clubId, @RequestBody NameValueList nameValueList) {
        clubService.modify(clubId, nameValueList);
    }

    @DeleteMapping("/{clubId}")
    public void delete(@PathVariable String clubId) {
        clubService.remove(clubId);
    }
}
