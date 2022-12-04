package ecommerce.example.ecommerce.controller;

import ecommerce.example.ecommerce.dto.ResponseDto;
import ecommerce.example.ecommerce.dto.user.SignInDto;
import ecommerce.example.ecommerce.dto.user.SignInResponseDto;
import ecommerce.example.ecommerce.dto.user.SignupDto;
import ecommerce.example.ecommerce.exceptions.CustomException;
import ecommerce.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {
    //    two apis
//    sigup
//    signin
    @Autowired
    UserService userService;




    @PostMapping("/signup")
    public ResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }
//    public ResponseEntity<User> createUser(@RequestBody  SignupDto signupDto) {
//
//            User user = new User(signupDto.getFirstName(),signupDto.getLastName(),signupDto.getEmail(),signupDto.getPassword());
//            userService.addUser(user);
//            return new ResponseEntity<User>(user,HttpStatus.CREATED);
//
//
//    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto){
        return userService.signIn(signInDto);

    }



    }

