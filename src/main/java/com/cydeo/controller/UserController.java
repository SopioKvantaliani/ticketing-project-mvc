package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService; //when we want from other class something we need to inject. Here we inject RoleService;

    public UserController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/create")
    public String createUser (Model model) {
      model.addAttribute("user", new UserDTO()); // We pass here new Object, because initially when we open page we need to see empty fields
      model.addAttribute("roles", roleService.findAll() );
      return "/user/create";


    }

}
