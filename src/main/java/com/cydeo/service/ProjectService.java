package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CrudService <ProjectDTO, String>{

    //why String? We pass String because we need unique id to catch projects.

    void complete (ProjectDTO project);
    List<ProjectDTO> getCountedListOfProjectDTO (UserDTO manager);

}
