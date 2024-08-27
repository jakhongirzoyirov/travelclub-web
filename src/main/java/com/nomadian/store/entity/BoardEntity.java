package com.nomadian.store.entity;

import com.nomadian.service.domain.club.Board;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "board")
public class BoardEntity {

    @Id
    private String id;

    @Min(0)
    private int sequence;

    @NotNull
    private String name;

    @NotNull
    private String adminEmail;

    @NotNull
    private String createDate;

    private String clubName;

    public Board toDomain() {
        Board board = new Board(this.name, this.adminEmail);
        board.setClubId(this.id);
        board.setCreateDate(this.createDate);
        board.setClubName(this.clubName);

        return board;
    }
}
