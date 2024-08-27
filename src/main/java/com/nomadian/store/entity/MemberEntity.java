package com.nomadian.store.entity;

import com.nomadian.service.domain.club.CommunityMember;
import com.nomadian.service.domain.club.vo.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "community_member")
@ToString
public class MemberEntity {
    @Id
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "Email is invalid")
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    private String nickName;

    @NotNull
    private LocalDate birthDay;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressEntity> addresses;

    public CommunityMember toDomain() {
        CommunityMember communityMember = new CommunityMember(this.email, this.password, this.name, this.nickName , this.phoneNumber, this.birthDay.toString());
        communityMember.setAddresses(getAddresses()
                .stream().map(AddressEntity::toDomain)
                .collect(Collectors.toList()));

        return communityMember;
    }
}
