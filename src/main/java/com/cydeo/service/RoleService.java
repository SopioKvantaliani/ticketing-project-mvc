package com.cydeo.service;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

public interface RoleService {

    RoleDTO save (RoleDTO user); //Save roles
    RoleDTO findById (Long id); //Find by Id Roles
    List<RoleDTO> findAll (); //FindAll roles
    void deleteById (Long id); //Delete roles, it is usually void, we don't need object.


}
