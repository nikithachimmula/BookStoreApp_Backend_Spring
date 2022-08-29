package com.bridgelabz.bookstoreapp_backend.dto;

import lombok.Data;

@Data
public class UserVerificationOtpDTO {
    private String email;
    private Integer otp;
}
