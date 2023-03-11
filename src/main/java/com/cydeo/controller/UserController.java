package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.service.impl.AbstractMapService;
import com.cydeo.service.impl.RoleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/create")
    public String createUser (Model model) {
      model.addAttribute("user", new UserDTO()); // We pass here new Object, because initially when we open page we need to see empty fields
//      model.addAttribute("roles", )
      return "/user/create";
    }


}
