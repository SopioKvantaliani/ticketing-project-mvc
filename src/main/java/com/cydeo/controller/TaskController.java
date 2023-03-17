package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/create") //end point
    public String createTask(Model model) {

        //attributes (project object, )
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll() );
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "/task/create";

    }

    @PostMapping("/create") //we need to follow here "action" end point from Html
    public String insertTask (TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";
    }
    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
       taskService.deleteById(id);

        return "redirect:/task/create";
    }
    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId, Model model) {

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "/task/update";

    }
//    @PostMapping("/update/{taskId}")
//    public String updateTask(@PathVariable ("taskId") Long taskId, TaskDTO task){ //TaskDTO task is the object we try to update;
//
//        task.setId(taskId);
//        /*
//        this code before updating object set's the same Id from the old object and than
//        we save the overwritten version of task.
//         */
//        taskService.update(task);
//
//        return "/task/create";
//    }
    /*
    when we set object we overwrite all the information. In order to catch the ID of the task and to set the same ID to the
    new object, developer decided to capture ID {taskID} and assigns the same one to the updated new object.
     */

    @PostMapping("/update/{id}")
    public String updateTask (TaskDTO task){
        taskService.update(task);
        return "redirect:/task/create";
    }

}
