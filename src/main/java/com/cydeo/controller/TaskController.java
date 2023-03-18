package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.scheduling.config.Task;
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
    /*
    This method posting some data, tasks. As tasks are coming from form, we don't use @PathVariable to catch id.
    Spring knows and manages itself.
     */

    @GetMapping("/employee/pending-tasks")
    public String employeePendingTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));
        return "/task/pending-tasks";
    }


    @GetMapping("/employee/archive")
    public String employeeArchivedTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasksByStatus(Status.COMPLETE));
        return "/task/archive";
    }
    @GetMapping("/employee/edit/{id}")
    public String employeeEditTask(@PathVariable Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("statuses", Status.values());//values is Enum ready method and displays all the statuses
        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));

        return "/task/status-update";

            /*
    Here we don't have any form, we don't send any object to the UI, so we need to use @PathVariable and catch
    the id like that.
     */
    }
 @PostMapping("/employee/update/{id}")
 public String employeeUpdateTask (TaskDTO task){
       taskService.updateStatus(task);
       return "redirect:/task/employee/pending-tasks";

 }

}



