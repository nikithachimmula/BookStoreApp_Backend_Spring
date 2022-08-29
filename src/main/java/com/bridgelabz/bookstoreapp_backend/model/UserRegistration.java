package com.bridgelabz.bookstoreapp_backend.model;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data

public class UserRegistration {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer userId;

    private String firstName;

    private String lastName;

    private String kyc;

    private String email;

    private String password;

    private LocalDate dob;

    private LocalDate registeredDate;

    private Boolean verify;

    private Integer otp;
}



