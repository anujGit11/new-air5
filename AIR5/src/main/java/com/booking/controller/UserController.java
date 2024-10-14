package com.booking.controller;

import com.booking.entity.PropertyUser;
import com.booking.payload.LoginDto;
import com.booking.payload.PropertyUserDto;
import com.booking.payload.TokenResponse;
import com.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/air5/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto propertyUserDto){

        PropertyUser propertyUser = userService.addUser(propertyUserDto);
        if(propertyUser!=null){
            return new ResponseEntity<>("Registratipn is succesfull", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Registration went wrong...",HttpStatus.INTERNAL_SERVER_ERROR);
    }



//    @PostMapping("/login")
//    public ResponseEntity<String> loginDto(@RequestBody LoginDto loginDto){
//        boolean status = userService.verifyLogin(loginDto);
//        if(status){
//            return new ResponseEntity<>("user signed in",HttpStatus.OK);
//
//        }
//        return new ResponseEntity<>("INVALID SIGNED IN",HttpStatus.UNAUTHORIZED);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> loginDto(@RequestBody LoginDto loginDto){
        String token = userService.verifyLogin(loginDto);
        if(token!=null){
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);
            return new ResponseEntity<>(tokenResponse,HttpStatus.OK);

        }
        return new ResponseEntity<>("INVALID SIGNED IN",HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/profile")
    public PropertyUser getCurrentUserProfile(@AuthenticationPrincipal PropertyUser user){

        return user;
    }



}
