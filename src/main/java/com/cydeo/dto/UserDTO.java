package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    @NotBlank //NotBlank is for String fields, for objects we use @NotNull
    @Size(max=15, min=2) //size of firstName
    private String firstName;

    @NotBlank
    @Size(max=15, min=2)
    private String lastName;

    @NotBlank
    @Email //email format userName only
    private String userName;

//    @NotBlank
//    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String passWord;

//    @NotNull
    private String confirmPassWord;


    private boolean enabled;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$") //this form should have 10 characters
    private String phone;

    @NotNull
    private RoleDTO role;

    @NotNull
    private Gender gender;

}
