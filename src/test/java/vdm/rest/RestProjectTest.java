package vdm.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import vdm.entity.Project;
import vdm.security.SecurityUser;
import vdm.service.ProjectService;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestProjectTest {

    @InjectMocks
    private RestProject restProject = new RestProject();

    @Mock
    private ProjectService projectService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityUser securityUser;

    @Mock
    private Project project;

    @Before
    public void setUp() throws Exception {
        when(securityUser.getUserId()).thenReturn(1);
        when(authentication.getPrincipal()).thenReturn(securityUser);
    }

    @Test
    public void getAllProjectOfUser() throws Exception {

        restProject.getAllProjectOfUser(authentication);
        verify(projectService).getProjectsOfDeveloper(anyInt());
        verify(authentication).getPrincipal();
    }

    @Test
    public void addProject() throws Exception {
        restProject.addProject(project, authentication);
        verify(projectService).addProject(project);
        verify(authentication).getPrincipal();
    }

    @Test
    public void getProjectsOfManager() throws Exception {
        restProject.getProjectsOfManager(authentication);
        verify(projectService).getProjectsOfManager(anyInt());
        verify(authentication).getPrincipal();
    }

}