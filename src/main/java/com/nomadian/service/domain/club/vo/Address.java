package com.nomadian.service.domain.club.vo;

import com.nomadian.store.entity.AddressEntity;
import com.nomadian.store.entity.ClubEntity;
import com.nomadian.store.entity.MembershipEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Address {
	private String zipCode;
	private String zipAddress;
	private String streetAddress;
	private String country;
	private AddressType addressType;

	public Address(String zipCode, String zipAddress, String streetAddress) {
		this.zipCode = zipCode;
		this.zipAddress = zipAddress;
		this.streetAddress = streetAddress;
		this.country = "South Korea";
		this.addressType = AddressType.Office;
	}

	public AddressEntity toEntity() {
		AddressEntity addressEntity = new AddressEntity();

		BeanUtils.copyProperties(this, addressEntity);
		addressEntity.setAddressType(getAddressType().toString());

		return addressEntity;
	}
}