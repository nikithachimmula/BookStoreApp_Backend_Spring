package com.bridgelabz.bookstoreapp_backend.service;

import com.bridgelabz.bookstoreapp_backend.dto.UserDTO;
import com.bridgelabz.bookstoreapp_backend.model.UserRegistration;
import com.bridgelabz.bookstoreapp_backend.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp_backend.util.EmailSenderService;
import com.bridgelabz.bookstoreapp_backend.util.TokenUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements  IUserService {


    @Autowired
    EmailSenderService emailService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRegistrationRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private TokenUtility tokenUtility;

    @Override
    public UserRegistration registerUserInApp(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserRegistration user = modelMapper.map(userDTO, UserRegistration.class);
        String otp = getRandomNumber();
        Integer intOtp = Integer.parseInt(otp);
        user.setOtp(intOtp);
        userRepository.save(user);
        emailService.sendEmail(user.getEmail(), "Otp for Verification", "Otp sent for verification purpose"
                + user.getFirstName() + "Please Click here to verify the Otp :-   "
                + "http://localhost:8080/user/verifyOtp" + intOtp);
        return user;
    }

    @Override
    public Boolean verifyOtp(String email, Integer otp) {
        UserRegistration user = userRepository.findByEmailId(email);
        Integer serveResponseOtp = user.getOtp();
        if (otp == null)
            return false;
        if (!(otp.equals(serveResponseOtp)))
            return false;
        userRepository.changeVerified(email);
        emailService.sendEmail(user.getEmail(), "Verification Successful", "Hi " + user.getFirstName() + ", You have successfully " +
                "verified your account. You can now login using Your email and password using this link. " + "http://localhost:8080/user/login");
        return true;
    }

    public static String getRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    @Override
    public String loginUser(String email, String password) {
        UserRegistration userLoginDetails = userRepository.findByEmailId(email);

        if (userLoginDetails == null)
            return "User not available, please enter your correct email and password";
        if (userLoginDetails.getVerify() == null)
            return "User not verified. please do the verification and try again. ";
        if (!(passwordEncoder.matches(password, userLoginDetails.getPassword())))
            return "Entered your User name or password incorrect";
        userRepository.save(userLoginDetails);
        String token = getToken(userLoginDetails.getEmail());
        return "User Login successfully and token is " + token;
    }

    private String getToken(String email) {
        UserRegistration userRegistration = userRepository.findByEmailId(email);
        String token = tokenUtility.createToken(userRegistration.getUserId());
        return token;
    }
    public Optional<UserRegistration> getUserDataByToken(String token) {
        int id = tokenUtility.decodeToken(token);
        Optional<UserRegistration> login = userRepository.findById(id);
        return login;
    }
    @Override
    public UserRegistration updateUserDataByToken(String token, UserDTO userDTO) {
        Integer id = tokenUtility.decodeToken(token);
        UserRegistration user = this.getUserDataById(id);
        modelMapper.map(userDTO,user);
        userRepository.save(user);
        return user;
    }

    @Override
    public UserRegistration getUserDataById(int userId) {
        return userRepository.findByUserId(userId);
    }
    @Override
    public UserRegistration deleteUserDataByToken(String token) {
        Integer id = tokenUtility.decodeToken(token);
        UserRegistration user = this.getUserDataById(id);
        userRepository.delete(user);
        return user;
    }
    @Override
    public String forgotPassword(String email, String password) {
        UserRegistration userDataByEmail = userRepository.findByEmailId(email);
        if (userDataByEmail == null)
            return "User not found";
        String newPassword = passwordEncoder.encode(password);
        System.out.println("newPassword: " + newPassword);
        userRepository.updateNewPassword(email, newPassword);
        return "Password updated successfully";
    }
}





