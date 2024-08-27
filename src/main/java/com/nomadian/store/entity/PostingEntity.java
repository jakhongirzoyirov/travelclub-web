package com.nomadian.store.entity;

import com.nomadian.service.domain.club.Posting;
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
@Table(name = "posting")
public class PostingEntity {

    @Id
    private String id;

    @NotNull
    private String title;

    @NotNull
    private String writerEmail;

    @NotNull
    private String contents;

    @NotNull
    private String writtenDate;

    @Min(0)
    private int readCount;

    private String boardId;

    public Posting toDomain() {
        Posting posting = new Posting();
        posting.setId(this.id);
        posting.setBoardId(this.boardId);
        posting.setTitle(this.title);
        posting.setContents(this.contents);
        posting.setWriterEmail(this.writerEmail);
        posting.setWrittenDate(this.writtenDate);
        posting.setReadCount(this.readCount);

        return posting;
    }
}
