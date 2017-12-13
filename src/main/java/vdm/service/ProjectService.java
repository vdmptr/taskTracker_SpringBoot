package vdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vdm.entity.Project;
import vdm.entity.Task;
import vdm.repository.ProjectRepository;
import vdm.repository.UserRepository;
import vdm.dto.ProjectDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectService {

    public ProjectService() {
    }

    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    @Autowired
    ProjectService(ProjectRepository projectRepository,
                   UserRepository userRepository){
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<ProjectDTO> getProjectsOfDeveloper(int userId){
        vdm.entity.User persistenceUser = userRepository.getOne(userId);
        List<Task> taskList = persistenceUser.getTaskList();
        List<Project> projectList = new ArrayList<>();
        return taskList.stream()
                       .map((Task::getProject))
                       .filter((p) -> {
                            if (projectList.contains(p)) {
                                return false;
                            }else {
                                projectList.add(p);
                                return true;
                            }
                       })
                       .map(this::convertProject)
                       .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsOfManager(int managerId){
        List<Project> projectList = projectRepository
                                        .findProjectByManagerId(managerId);
        return projectList.stream()
                          .map(this::convertProject)
                          .collect(Collectors.toList());
    }

    public ProjectDTO addProject(Project project){
        Project persistenceProject = projectRepository.saveAndFlush(project);
        return convertProject(persistenceProject);
    }

    private ProjectDTO convertProject(Project project){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(project.getName());
        projectDTO.setProjectId(project.getProjectId());
        return projectDTO;
    }
}
