package com.nomadian.store.entity;

import com.nomadian.service.domain.club.Membership;
import com.nomadian.service.domain.club.TravelClub;
import com.nomadian.service.domain.club.vo.RoleInClub;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "membership")
public class MembershipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "travel_club_id")
    private ClubEntity travelClub;

    @NotNull
    private String memberEmail;

    @NotNull
    private String role;

    @NotNull
    private String joinDate;


    public Membership toDomain() {
        Membership membership = new Membership(String.valueOf(this.travelClub.getId()), this.memberEmail);
        membership.setRole(RoleInClub.valueOf(this.role));
        membership.setJoinDate(this.joinDate);

        return membership;
    }
}
