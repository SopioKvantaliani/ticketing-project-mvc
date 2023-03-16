package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {
    @GetMapping("/create") //end point
    public String createTask(Model model) {

        //attributes (project object, )


        return "/task/create";

    }

}
