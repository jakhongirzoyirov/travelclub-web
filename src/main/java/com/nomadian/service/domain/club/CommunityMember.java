package com.nomadian.service.domain.club;

import com.google.gson.Gson;
import com.nomadian.service.domain.club.vo.Address;
import com.nomadian.shared.NameValue;
import com.nomadian.shared.NameValueList;
import com.nomadian.store.entity.MemberEntity;
import com.nomadian.util.exception.InvalidEmailException;
import com.nomadian.util.exception.InvalidPhoneNumberException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CommunityMember {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String nickName;
    private String birthDay;

    private List<Address> addresses;

	/*public CommunityMember() {
		super();
		this.addresses = new ArrayList<>();
	}*/


    public CommunityMember(String email, String password, String name, String nickName, String phoneNumber, String birthDay) {
        super();
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Name:").append(name);
        builder.append(", email:").append(email);
        builder.append(", nickname:").append(nickName);
        builder.append(", phone number:").append(phoneNumber);
        builder.append(", birthDay:").append(birthDay);

		/*if (addresses != null) {
			int i = 1;
			for (Address address : addresses) {
				builder.append(", Address[").append(i).append("]").append(address.toString());
			}
		}*/

        return builder.toString();
    }

    public MemberEntity toEntity() {
        MemberEntity memberEntity = new MemberEntity();
        BeanUtils.copyProperties(this, memberEntity);
        memberEntity.setBirthDay(LocalDate.parse(getBirthDay()));
        if (getAddresses() != null) {
            memberEntity.setAddresses(getAddresses().stream()
                    .map(Address::toEntity)
                    .collect(Collectors.toList()));
        }
        return memberEntity;
    }

    /*public void checkValidation() {
        checkEmailValidation(email);
    }

    public String checkEmailValidation(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        boolean valid = m.matches();

        if (!valid) {
            throw new InvalidEmailException("Email is invalid.");
        }

        return email;
    }

    public String checkPhoneNumberValidation(String phoneNumber) {
        if (phoneNumber == null) {
            throw new InvalidPhoneNumberException("Phone number is null.");
        }
        String pPattern = "^(010-?\\d{4}-?\\d{4})$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pPattern);
        java.util.regex.Matcher m = p.matcher(phoneNumber);
        boolean valid = m.matches();

        if (!valid) {
            throw new InvalidPhoneNumberException("Phone number is invalid.");
        }

        return phoneNumber;
    }*/         // validations are used

    public void modifyValues(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "password":
                    this.password = value;
                    break;
                case "name":
                    this.name = value;
                    break;
                case "phoneNumber":
                    this.phoneNumber = value;
                    break;
                case "nickName":
                    this.nickName = value;
                    break;
                case "birthDay":
                    this.birthDay = value;
                    break;
            }
        }
    }
}
