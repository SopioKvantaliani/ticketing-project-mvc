package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService <ProjectDTO, String>  implements ProjectService {

    @Override
    public ProjectDTO save(ProjectDTO project) {

        if(project.getProjectStatus()==null) //we add condition, otherwise all projects would have "Open" Status
        project.setProjectStatus(Status.OPEN); //whenever we save new project status will be "Open"
        return super.save(project.getProjectCode(), project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void update(ProjectDTO object) {
        if(object.getProjectStatus()==null){
            //when we try to update in the form, this project has status in the database, it shouldn't be null.
            //that's why I need to catch this status and keep the same one after updating.
            //go to database, find project by ID and getProjectStatus and set whatever you are updating;
            object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());
        }
        super.update(object.getProjectCode(), object);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETE);

    }
}
