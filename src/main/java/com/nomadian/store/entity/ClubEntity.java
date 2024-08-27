package com.nomadian.store.entity;

import com.nomadian.service.domain.club.TravelClub;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="travel_club")
@ToString
public class ClubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // pk

    @Size(min = 3, message = "Name length must be minimum 3")
    private String name;

    @Size(min = 5, message = "Intro length must be minimum 5")
    private String intro;

    @NotNull
    private LocalDate foundationTime;

    @OneToMany(mappedBy = "travelClub", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MembershipEntity> membershipList;

    /*public ClubEntity(TravelClub travelClub) {
        BeanUtils.copyProperties(travelClub, this);
        this.foundationTime = LocalDate.parse(travelClub.getFoundationTime());
        if (travelClub.getMembershipList() != null) {
            this.membershipList = travelClub.getMembershipList().stream()
                    .map(membership -> {
                        MembershipEntity membershipEntity = new MembershipEntity();
                        // Set other properties as needed
                        membershipEntity.setTravelClub(this); // Important to set bidirectional relationship
                        return membershipEntity;
                    })
                    .collect(Collectors.toList());
        }
    }*/

    public TravelClub toDomain() {
        TravelClub travelClub = new TravelClub(this.name, this.intro);
        travelClub.setId(this.id.toString());
        travelClub.setFoundationTime(this.foundationTime.toString());
        travelClub.setMembershipList(this.membershipList.stream().map(membershipEntity -> membershipEntity.toDomain()).collect(Collectors.toList()));

        return travelClub;
    }
}
