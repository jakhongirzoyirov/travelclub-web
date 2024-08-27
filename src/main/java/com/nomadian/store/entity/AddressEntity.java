package com.nomadian.store.entity;

import com.nomadian.service.domain.club.TravelClub;
import com.nomadian.service.domain.club.vo.Address;
import com.nomadian.service.domain.club.vo.AddressType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zipCode;
    private String zipAddress;
    private String streetAddress;
    private String country;
    private String addressType;


    public Address toDomain() {
        Address address = new Address(this.zipCode, this.zipAddress, this.streetAddress);
        address.setCountry(this.country);
        address.setAddressType(AddressType.valueOf(this.addressType));

        return address;
    }
}
