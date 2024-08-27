package com.nomadian.controller.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostingDto implements Serializable {
    @NotNull
    private String title;
    @NotNull
    private String writerEmail;
    @NotNull
    private String contents;
}
