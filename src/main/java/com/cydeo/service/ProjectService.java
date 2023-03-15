package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

public interface ProjectService extends CrudService <ProjectDTO, String>{

    //why String? We pass String because we need unique id to catch projects.

}
