package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.CrudService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl extends AbstractMapService <TaskDTO, Long> implements TaskService {


    @Override
    public TaskDTO save(TaskDTO task) {
        if (task.getTaskStatus()==null )
            task.setTaskStatus(Status.OPEN);
        if (task.getAssignedDate()==null)
            task.setAssignedDate(LocalDate.now());

        if (task.getId()==null){
            task.setId(UUID.randomUUID().getLeastSignificantBits());
            /*When we create a new task it we doesn't assign Id, but when we delete or update, we need Id, otherwise
            it will give us error.*/
        }

        return super.save(task.getId(), task);
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
    public void update(TaskDTO task) {
        super.update(task.getId(), task);
    }
}
