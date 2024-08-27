package com.nomadian.service.domain.club;

import com.nomadian.service.domain.Entity;
import com.nomadian.shared.NameValue;
import com.nomadian.shared.NameValueList;
import com.nomadian.store.entity.BoardEntity;
import com.nomadian.util.helper.DateUtil;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Data
@NoArgsConstructor
public class Board implements Entity {
    private String clubId;
    private int sequence;
    private String name;
    private String adminEmail;
    private String createDate;

    private String clubName;

    public Board(String name, String adminEmail) {
        this.name = name;
        this.adminEmail = adminEmail;
        this.createDate = DateUtil.today();
    }

    public Board(TravelClub travelClub, String name, String adminEmail) {
        this.clubName = travelClub.getName();
        this.name = name;
        this.adminEmail = adminEmail;
        this.createDate = DateUtil.today();
    }

    public BoardEntity toEntity() {
        BoardEntity boardEntity = new BoardEntity();
        BeanUtils.copyProperties(this, boardEntity);

        return boardEntity;
    }

    public void modifyValues(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    this.name = value;
                    break;
                case "intro":
                    this.adminEmail = value;
                    break;
            }
        }
    }

    @Override
    public String getId() {
        return clubId;
    }
}
