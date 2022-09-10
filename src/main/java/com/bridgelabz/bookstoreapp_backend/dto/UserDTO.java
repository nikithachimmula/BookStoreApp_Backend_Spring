package com.bridgelabz.bookstoreapp_backend.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class UserDTO {
    @Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="FirstName is Not valid")
    public String firstName;

    @Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="LastName is Not valid")
    public String lastName;

    @Email
    private String email;

    @NotEmpty(message = "kyc can not be empty")
    private String kyc;

    @NotEmpty(message = "password cannot be empty")
    private String password;

    private LocalDate dob;


    private LocalDate registeredDate;


}
