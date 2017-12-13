package vdm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vdm.entity.Project;
import vdm.security.SecurityUser;
import vdm.service.ProjectService;
import vdm.dto.ProjectDTO;

import java.util.List;

@RestController
@RequestMapping(value = "/project")
public class RestProject {

    private ProjectService projectService;

    public RestProject() {
    }

    @Autowired
    RestProject(ProjectService projectService){
        this.projectService = projectService;
    }

    @Secured("ROLE_DEVELOPER")
    @GetMapping(value = "/developer",
                produces = "application/json")
    public List<ProjectDTO> getAllProjectOfUser(Authentication authentication){
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        return projectService.getProjectsOfDeveloper(securityUser.getUserId());
    }

    @Secured("ROLE_MANAGER")
    @PostMapping(value = "",
                 produces = "application/json")
    public ProjectDTO addProject(@RequestBody Project project,
                                 Authentication authentication){
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        int managerId = securityUser.getUserId();
        project.setManagerId(managerId);
        return projectService.addProject(project);
    }

    @Secured("ROLE_MANAGER")
    @GetMapping(value = "/manager/",
                produces = "application/json")
    public List<ProjectDTO> getProjectsOfManager(Authentication authentication){
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        return projectService.getProjectsOfManager(securityUser.getUserId());
    }
}
