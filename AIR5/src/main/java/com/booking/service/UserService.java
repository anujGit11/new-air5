package com.booking.service;

import com.booking.entity.PropertyUser;
import com.booking.payload.LoginDto;
import com.booking.payload.PropertyUserDto;
import com.booking.repository.PropertyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private PropertyUserRepository userRepository;

    @Autowired
    private JWTService jwtService;


    public PropertyUser addUser(PropertyUserDto propertyUserDto){

        PropertyUser user = new PropertyUser();
        user.setFirstName(propertyUserDto.getFirstName());
        user.setLastName(propertyUserDto.getLastName());
        user.setEmail(propertyUserDto.getEmail());
        user.setUsername(propertyUserDto.getUsername());
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(),BCrypt.gensalt(10)));
        user.setUserRole(propertyUserDto.getUserRole());

        PropertyUser savedUser = userRepository.save(user);
        return savedUser;
    }

//    public boolean verifyLogin(LoginDto loginDto) {
//        Optional<PropertyUser> opUser = userRepository.findByUsername(loginDto.getUsername());
//        if(opUser.isPresent()){
//            PropertyUser propertyUser = opUser.get();
//            return BCrypt.checkpw(loginDto.getPassword(),propertyUser.getPassword());
//        }
//        return false;
//    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<PropertyUser> opUser = userRepository.findByUsername(loginDto.getUsername());
        if(opUser.isPresent()){
            PropertyUser propertyUser = opUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(),propertyUser.getPassword())){
                  return jwtService.generateToken(propertyUser);

            }
        }
        return null;
    }
}
