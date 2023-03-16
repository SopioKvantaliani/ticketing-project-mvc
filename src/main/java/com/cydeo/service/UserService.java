package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService <UserDTO, String> { // 1st parameter whatever object you are working with, 2nd Parameter UserName which is String.

    //if something is unique for Service, we can create here.
    //in CrudService is only for common methods.

    List<UserDTO> findManagers();
}
