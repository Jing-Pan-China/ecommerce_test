package ecommerce.example.ecommerce.service;

import ecommerce.example.ecommerce.dto.ResponseDto;
import ecommerce.example.ecommerce.dto.user.SignInDto;
import ecommerce.example.ecommerce.dto.user.SignInResponseDto;
import ecommerce.example.ecommerce.dto.user.SignupDto;
import ecommerce.example.ecommerce.exceptions.AuthenticationFailException;
import ecommerce.example.ecommerce.exceptions.CustomException;
import ecommerce.example.ecommerce.model.AuthenticationToken;
import ecommerce.example.ecommerce.model.User;
import ecommerce.example.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;


    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        if (Objects.nonNull(userRepo.findByEmail(signupDto.getEmail()))) {
            throw new CustomException("user already present");
        }

        String encryptedpassword = signupDto.getPassword();
        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), encryptedpassword);

        userRepo.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success", "user created successfully");


        return responseDto;

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
//        find user by email
        User user = userRepo.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
// compare the password in DB
//  if password match
        AuthenticationToken token = authenticationService.getToken(user);


//    retrive the token
        if (Objects.isNull(token)) {
            throw new CustomException("token is not present");

        }
        return new SignInResponseDto("sucess", token.getToken());
    }



//    public User addUser(User user){
//        User newUser=userRepo.save(user);
//        return newUser;
//    }




    }

