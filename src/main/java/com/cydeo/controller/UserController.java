package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String insertUser (@Valid @ModelAttribute ("user") UserDTO user, BindingResult bindingResult, Model model){ //Make sure the user coming from form is @Valid
    if (bindingResult.hasErrors()){  //if found incorrect object, show view again;
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());
        return "/user/create";
    }
        //(user object, roles, users)

    model.addAttribute("user", new UserDTO()); //when we click on the save, this method executes, we need empty form. User object gone;
    model.addAttribute("roles", roleService.findAll());  //We want to see roles in the form; roles gone
    userService.save(user);
    model.addAttribute("users", userService.findAll()); //users gone

        return "/user/create";  // We provide in the method all the attributes whatever this view provides.

    }

      @GetMapping("/update/{username}") //{username} is path-variable, and we need to pass as parameter.
      public String editUser (@PathVariable ("username") String username, Model model){ //We use String because we need to return view. Always String

        //(user object, roles, users. So we can redirect and add edit functionality)
          model.addAttribute("user", userService.findById(username));
          model.addAttribute("roles", roleService.findAll());
          model.addAttribute("users", userService.findAll()); //users are the list of user objects.
        /*
        "username" should come from UI side, that's why we need to use either query parameter, or Path-variable
        When we click 'update' we don't post anything, we're just retrieving data, that's why we need to use
        @GetMapping and @PathVariable.
         */

        return "/user/update";  //firstly we need to figure out what will be the return type view, because view decides what we need inside the method.

    }

    /*
    If we redirect link and say "redirect:/user/create" we don't need Model interface and all the model.addAttributes.
    The only thing we need to do is to call method =  userService.save(user), to save users from UI.
    Why we pass @ModelAttribute ("user"), because 'save()' method requires user object.

    We use @ModelAttribute("user") ---> to bring 'user' object field in this method.

    Anything that is related to User we should build inside the userController.

     */


    @PostMapping ("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO user){ //I am saying, I want to use "user" attribute inside userService.update method

       // update that user
        //Do we have Service to update user?

        userService.update(user);

        return "redirect:/user/create"; //redirecting because after update we need empty userName;

        /*
        @ModelAttribute is inside your Java Code and gives you right to use any attributes inside the java Code.
        Here we say that, we need to use 'user' attribute inside Java code;
        eg. 'user' here hold attribute when we click for update, before saving.
         */
    }

    @GetMapping("/delete/{username}") //which object we are deleting? that's why we need to put here username
    public String deleteUser (@PathVariable ("username") String username){

        userService.deleteById(username);

        return "redirect:/user/create";
    }

}
