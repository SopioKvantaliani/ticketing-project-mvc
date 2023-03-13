package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService; //when we want from other class something we need to inject. Here we inject RoleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser (Model model) {
      model.addAttribute("user", new UserDTO()); // We pass here new Object, because initially when we open page we need to see empty fields
      model.addAttribute("roles", roleService.findAll() );
      model.addAttribute("users", userService.findAll() );
      return "/user/create";


    }

    @PostMapping("/create")

    public String insertUser (@ModelAttribute ("user") UserDTO user,  Model model){

        //(user object, roles, users)

    model.addAttribute("user", new UserDTO()); //when we click on the save, this method executes, we need empty form. User object gone;
    model.addAttribute("roles", roleService.findAll());  //We want to see roles in the form; roles gone
        userService.save(user);
    model.addAttribute("users", userService.findAll()); //users gone

        return "/user/create";  // We provide in the method all the attributes whatever this view provides.

    }

    /*
    If we redirect link and say "redirect:/user/create" we don't need Model interface and all the model.addAttributes.
    The only thing we need to do is to call method =  userService.save(user), to save users from UI.
    Why we pass @ModelAttribute ("user"), because 'save()' method requires user object.

    We use @ModelAttribute("user") ---> to bring 'user' object field in this method.

     */

}
