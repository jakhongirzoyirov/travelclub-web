package com.nomadian.service.domain.club;

import com.nomadian.service.domain.Entity;
import com.nomadian.shared.NameValue;
import com.nomadian.shared.NameValueList;
import com.nomadian.store.entity.PostingEntity;
import com.nomadian.util.helper.DateUtil;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
public class Posting implements Entity {

    @NotNull
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

    public Posting() {
        this.readCount = 0;
    }

    public Posting(Board board, String title, String writerEmail, String contents) {
        super();
        this.boardId = "Board:" + boardId + "-" + board.getSequence();
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.writtenDate = DateUtil.today();
    }

    public Posting(String boardId, String title, String writerEmail, String contents) {
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.boardId = boardId;
    }

    public PostingEntity toEntity() {
        PostingEntity postingEntity = new PostingEntity();
        BeanUtils.copyProperties(this, postingEntity);
        return postingEntity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Posting Id : ").append(id);
        sb.append(", title : ").append(title);
        sb.append(", writerEmail : ").append(writerEmail);
        sb.append(", contents : ").append(contents);
        sb.append(", writtenDate : ").append(writtenDate);
        sb.append(", read count : ").append(readCount);
        sb.append(", boardId : ").append(boardId);

        return sb.toString();
    }

    public void modifyValues(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    this.title = value;
                    break;
                case "intro":
                    this.contents = value;
                    break;
            }
        }
    }

    @Override
    public String getId() {
        return id;
    }
}
