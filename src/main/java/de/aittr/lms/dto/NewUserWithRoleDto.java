package de.aittr.lms.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class NewUserWithRoleDto {

        String cohort;
        String email;
        String firstName;
        String lastName;
        String country;
        String role;
        String phone;
       // String state;

}
