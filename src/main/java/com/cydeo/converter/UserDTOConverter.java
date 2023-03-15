package com.cydeo.converter;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


//@SpringBootConfiguration = news, no need to put, because Spring does it automatically
@Component
public class UserDTOConverter implements Converter <String, UserDTO> { //we want to convert from String into UserDTO/Object

    UserService userService;

    public UserDTOConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO convert(String source) {
        return userService.findById(source);
    }
}

/*
It's ready converter interface, we want to convert String into UserDTO.
This converter interface belongs Spring package, and you don't need to put @
you give me the String username, I'll go to dataBase, and I'll bring the Object = this is the logic here.

 */