package com.bridgelabz.bookstoreapp_backend.controller;

import com.bridgelabz.bookstoreapp_backend.dto.UserDTO;
import com.bridgelabz.bookstoreapp_backend.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp_backend.dto.UserVerificationOtpDTO;
import com.bridgelabz.bookstoreapp_backend.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp_backend.model.UserRegistration;
import com.bridgelabz.bookstoreapp_backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRegistrationController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IUserService userRegistrationService;

    //hello
    @GetMapping("/")
    public String hello() {
        return "welcome to user registration page";
    }


    final static String SUCCESS = "Entered Otp is valid, and Registration was successful.";
    final static String FAIL = "Entered OTP was not valid!, Registration failed!, please try again";

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addUserInBookStore(@Valid @RequestBody UserDTO userDTO) {
        UserRegistration userRegistration = userRegistrationService.registerUserInApp(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Registered Successfully", userRegistration);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public String userLogin(@RequestParam String email, @RequestParam String password) {
        UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);
        String response = userRegistrationService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return response;
    }

    @PostMapping({"/verifyotp"})
    public String verifyOtp(@Valid @RequestBody UserVerificationOtpDTO userNameOtpDTO) {
        String email = userNameOtpDTO.getEmail();
        Integer otp = userNameOtpDTO.getOtp();
        Boolean isVerifyOtp = userRegistrationService.verifyOtp(email, otp);
        if (!isVerifyOtp)
            return FAIL;
        return SUCCESS;
    }
    @GetMapping(value = "/getByToken/{token}")
    public ResponseEntity<ResponseDTO> getUserDataByToken(@PathVariable String token) {
        Optional<UserRegistration> User = userRegistrationService.getUserDataByToken(token);
        ResponseDTO responseDTO = new ResponseDTO("Data retrieved successfully (:",User);
        return new ResponseEntity(responseDTO,HttpStatus.OK);

    }
    @PutMapping("/updateByToken/{token}")
    public ResponseEntity<String> updateUserDataByToken(@PathVariable String token,@Valid @RequestBody UserDTO userDTO){
        UserRegistration entity = userRegistrationService.updateUserDataByToken(token,userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Record updated successfully",entity);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }
    @DeleteMapping(value = {"/deleteByToken/{token}"})
    public ResponseEntity<ResponseDTO> deleteUserDataByToken(@PathVariable String token) {
        UserRegistration entity = userRegistrationService.deleteUserDataByToken(token);
        ResponseDTO responseDTO = new ResponseDTO("Data Deleted Successfully!!!",
                "ID number: "+entity.getUserId() + " DELETED!!!");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/forgotpassword")
    public String forgotPassword(@RequestParam String email, @RequestParam String password) {
        return userRegistrationService.forgotPassword(email, password);
    }
}