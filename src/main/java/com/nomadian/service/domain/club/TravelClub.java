package com.nomadian.service.domain.club;

import com.google.gson.Gson;
import com.nomadian.service.domain.AutoIdEntity;
import com.nomadian.shared.NameValue;
import com.nomadian.shared.NameValueList;
import com.nomadian.store.entity.ClubEntity;
import com.nomadian.store.entity.MembershipEntity;
import com.nomadian.util.helper.DateUtil;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class TravelClub implements AutoIdEntity {
	private static final int MINIMUM_NAME_LENGTH =  3;
	private static final int MINIMUM_INTRO_LENGTH =  10;

	private String id;
	@Size(min = 3, message = "Name length must be minimum 3")
	private String name;
	@Size(min = 5, message = "Intro length must be minimum 5")
	private String intro;
	@NotNull
	private String foundationTime;
	private List<Membership> membershipList;

	public TravelClub(String name, String intro) {
		super();
		this.name = name;
		this.intro = intro;
		this.foundationTime = DateUtil.today();
	}

	public ClubEntity toEntity() {
		ClubEntity clubEntity = new ClubEntity();
		BeanUtils.copyProperties(this, clubEntity);
		clubEntity.setFoundationTime(LocalDate.parse(getFoundationTime()));
		if (getMembershipList() != null) {
			clubEntity.setMembershipList(getMembershipList().stream()
					.map(membership -> {
						MembershipEntity membershipEntity = new MembershipEntity();
						// Set other properties as needed
						membershipEntity.setTravelClub(clubEntity); // Important to set bidirectional relationship
						return membershipEntity;
					})
					.collect(Collectors.toList()));
		}

		return clubEntity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Travel Club Id:").append(id);
		builder.append(", name:").append(name);
		builder.append(", intro:").append(intro);
		builder.append(", foundation day:").append(foundationTime);

		return builder.toString();
	}

	/*public void checkValidation() {
		checkNameValidation(name);
		checkIntroValidation(intro);
	}

	private void checkNameValidation(String name) {
		if (name.length() < TravelClub.MINIMUM_NAME_LENGTH) {
			throw new IllegalArgumentException("\t > Name should be longer than " + TravelClub.MINIMUM_NAME_LENGTH);
		}
	}

	private void checkIntroValidation(String intro) {
		if (intro.length() < TravelClub.MINIMUM_INTRO_LENGTH) {
			throw new IllegalArgumentException("\t > Intro should be longer than " + TravelClub.MINIMUM_INTRO_LENGTH);
		}
	}*/

	public void modifyValues(NameValueList nameValues) {
		for (NameValue nameValue : nameValues.getNameValues()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "name":
//					checkNameValidation(value);
					this.name = value;
					break;
				case "intro":
//					checkIntroValidation(value);
					this.intro = value;
					break;
			}
		}
	}

	@Override
	public String getIdFormat() {
		return "%05d";
	}

	@Override
	public void setAutoId(String autoId) {
		this.id = autoId;
	}
}
