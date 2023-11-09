package de.aittr.lms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class NewUserDto {

    String cohort;
    String email;
    String firstName;
    String lastName;
    String country;
    String phone;

}
