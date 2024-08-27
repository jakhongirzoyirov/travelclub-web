package com.nomadian.controller.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TravelClubDto  {
    @Size(min = 3, message = "Name length must be minimum 3")
    private String name;

    @Size(min = 5, message = "Intro length must be minimum 5")
    private String intro;
}
