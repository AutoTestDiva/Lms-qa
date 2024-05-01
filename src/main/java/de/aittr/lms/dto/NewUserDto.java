package de.aittr.lms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class NewUserDto {

    private String cohort;
    private String email;
    private String firstName;
    private String lastName;
    private String country;
    private String phone;
    private String role;
    private String state;

}
