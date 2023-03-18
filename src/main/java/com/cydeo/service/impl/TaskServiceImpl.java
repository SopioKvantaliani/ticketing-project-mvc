package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.CrudService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService <TaskDTO, Long> implements TaskService {


    @Override
    public TaskDTO save(TaskDTO task) { //task is new task we try to overwrite;

        if(task.getTaskStatus() == null)
            task.setTaskStatus(Status.OPEN);

        if(task.getAssignedDate() == null)
            task.setAssignedDate(LocalDate.now());

        if(task.getId()==null)
            task.setId(UUID.randomUUID().getMostSignificantBits());

        return super.save(task.getId(),task);

            /*When we create a new task we doesn't assign Id, but when we delete or update, we need Id, otherwise
            it will give us error.*/
        }



    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) { //those variables we can put any, whatever name we want
        super.deleteById(id);
    }

    @Override
    public void update(TaskDTO task) {//task is new task we try to overwrite;

        TaskDTO foundTask = findById(task.getId());//Old Task that was in the system;

        task.setTaskStatus(foundTask.getTaskStatus());//setting for the new task. we are not changing current status.
        task.setAssignedDate(foundTask.getAssignedDate());

        super.update(task.getId(),task);
    }

    @Override
    public List<TaskDTO> findTasksByManager(UserDTO manager) {
        return findAll()
                .stream()
                .filter(task -> task.getProject().getAssignedManager().equals(manager))
                .collect(Collectors.toList());

        /*
        bring all projects, put in stream, filter all tasks, get project, get assigned manager equals to "manager",
        which means any manager we are looking;
         */
    }

    @Override
    public List<TaskDTO> findAllTasksByStatusIsNot(Status status) {

        return findAll().stream().filter(task->!task.getTaskStatus().equals((status))).collect(Collectors.toList());
        //find all tasks and filter tasks that does not have "status" (means complete status);
    }

    @Override
    public List<TaskDTO> findAllTasksByStatus(Status status) {
        return findAll().stream().filter(task->task.getTaskStatus().equals((status))).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO task) {
        findById(task.getId()).setTaskStatus(task.getTaskStatus());
        /*We are changing old status first, and then we use update;
        In this code we took Task status, updated and put it back.
         */
        update(task); //Here we update task with new status.
    }
}
