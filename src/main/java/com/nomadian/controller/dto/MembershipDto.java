package com.nomadian.controller.dto;

import com.nomadian.service.domain.club.vo.RoleInClub;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDto implements Serializable {

    @NotNull
    private String clubId;
    @NotNull
    private String memberEmail;
    @NotNull
    private RoleInClub role;
}
