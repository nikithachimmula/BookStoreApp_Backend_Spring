package com.bridgelabz.bookstoreapp_backend.service;

import com.bridgelabz.bookstoreapp_backend.dto.UserDTO;
import com.bridgelabz.bookstoreapp_backend.model.UserRegistration;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserRegistration registerUserInApp(UserDTO userDTO);

    Boolean verifyOtp(String email, Integer otp);

    String loginUser(String email, String password);


    Optional<UserRegistration> getUserDataByToken(String token);

    UserRegistration updateUserDataByToken(String token, UserDTO userDTO);

    UserRegistration getUserDataById(int userId);

    UserRegistration deleteUserDataByToken(String token);

    String forgotPassword(String email, String password);
}
