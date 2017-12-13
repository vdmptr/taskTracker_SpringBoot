package vdm.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import vdm.entity.Project;
import vdm.entity.Task;
import vdm.repository.ProjectRepository;
import vdm.repository.UserRepository;
import vdm.dto.ProjectDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService = new ProjectService();

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Project project;

    @Mock
    private vdm.entity.User user;

    @Mock
    private Task task;

    @Before
    public void setUp() throws Exception {

        int userId = 1;
        int managerId = 1;

        when(project.getName()).thenReturn("userNameTest");
        when(project.getProjectId()).thenReturn(5);
        when(task.getProject()).thenReturn(project);
        when(userRepository.getOne(userId)).thenReturn(user);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(user.getTaskList()).thenReturn(taskList);
        List<Project> projectList = new ArrayList<>();
        projectList.add(project);
        when(projectRepository.findProjectByManagerId(managerId))
                                                .thenReturn(projectList);
        when(projectRepository.saveAndFlush(project))
                                                 .thenReturn(project);
    }

    @Test
    public void getProjectsOfDeveloper() throws Exception {
        int userId = 1;

        List<ProjectDTO> projectDTOList = projectService
                                              .getProjectsOfDeveloper(userId);
        assertEquals(projectDTOList.get(0).getProjectId(),5);
    }

    @Test
    public void getProjectsOfManager() throws Exception {
        int managerId = 1;
        List<ProjectDTO> projectDTOList = projectService
                                              .getProjectsOfManager(managerId);
        assertEquals(projectDTOList.get(0).getProjectId(),5);
    }

    @Test
    public void addProject() throws Exception {
        ProjectDTO projectDTO = projectService.addProject(project);

        assertEquals(projectDTO.getProjectId(),5);
    }

}