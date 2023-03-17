package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

public interface TaskService extends CrudService <TaskDTO, Long>{

    //we put TaskDTO object and one unique identification Id;

    List<TaskDTO> findTasksByManager (UserDTO manager);

}
