package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService <ProjectDTO, String>  implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

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

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
        List<ProjectDTO> projectList =
                findAll()
                        .stream()
                        .filter(project->project.getAssignedManager().equals(manager))
                        .map(project -> {

                            List<TaskDTO> taskList = taskService.findTasksByManager(manager);
                            int completeTaskCounts = (int) taskList.stream() //we need to cast, because count method returns Long Always;
                                    .filter(task->task.getProject()
                                            .equals(project) && task.getTaskStatus()==Status.COMPLETE ).count();
                            int unfinishedTaskCounts = (int)taskList.stream()
                                    .filter(task->task.getProject()
                                            .equals(project) && task.getTaskStatus()!=Status.COMPLETE).count();//not equals

                            project.setCompleteTaskCounts(completeTaskCounts);
                            project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                            return project; //As we did inside curly braces it looks return;

                        })
                        .collect(Collectors.toList());
        return projectList;

        /*
        "projectList" coming from database doesn't have complete and unfinished task fields. That's why
        we tell Map "do something to this object, something what? set the fields".
         */
    }


}
