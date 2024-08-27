package com.nomadian.service.domain.club;

import com.nomadian.service.domain.club.vo.RoleInClub;
import com.nomadian.shared.NameValue;
import com.nomadian.shared.NameValueList;
import com.nomadian.store.entity.MembershipEntity;
import com.nomadian.util.helper.DateUtil;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class Membership {
	private String clubId;
	private String memberEmail;
	private RoleInClub role;
	private String joinDate;


	public Membership(String clubId, String memberEmail) {
		this.clubId = clubId;
		this.memberEmail = memberEmail;
		this.role = RoleInClub.Member;
		this.joinDate = DateUtil.today();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("club Id:").append(clubId);
		builder.append(", member Id:").append(memberEmail);
		builder.append(", role:").append(role.name());
		builder.append(", join date:").append(joinDate);

		return builder.toString();
	}

	public MembershipEntity toEntity(TravelClub travelClub) {
		MembershipEntity membershipEntity = new MembershipEntity();

		BeanUtils.copyProperties(this, membershipEntity);
		membershipEntity.setTravelClub(travelClub.toEntity());

		return membershipEntity;
	}

	public void modifyValues(NameValueList nameValueList) {
		for (NameValue nameValue : nameValueList.getNameValues()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "role":
					this.role = RoleInClub.valueOf(value);
					break;
			}
		}
	}
}