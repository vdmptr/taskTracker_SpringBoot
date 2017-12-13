package vdm.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import vdm.entity.Task;
import vdm.entity.User;
import vdm.repository.TaskRepository;
import vdm.service.TaskService;
import vdm.dto.DescriptionDTO;
import javax.servlet.http.HttpSession;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestTaskTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskService taskService;

    @Mock
    private Task task;

    @Mock
    private User user;

    @Mock
    private HttpSession session;

    @InjectMocks
    private RestTask restTask;

    @Test
    public void getAllTaskOfProject() throws Exception {
        int projectId = 1;
        restTask.getAllTaskOfProject(projectId);
        verify(taskService).getTasksOfProject(projectId);
    }

    @Test
    public void addTaskToProject() throws Exception {
        int projectId = 1;
        restTask.addTaskToProject(projectId, task);
        verify(taskService).addTaskToProject(task, projectId);
    }

    @Test
    public void addTaskOfDeveloper() throws Exception {
        int projectId = 1;
        int developerId = 1;
        restTask.addTaskOfDeveloper(projectId, developerId, task);
        verify(taskService).addTaskToProject(task, projectId);
        verify(taskService).addTaskToUser(task, developerId);
    }

    @Test
    public void updateTaskForUser() throws Exception {
        int taskId = 1;
        restTask.updateTaskForUser(taskId, user);
        verify(taskService).updateTaskForUser(taskId, user);
    }

    @Test
    public void updateStatusTask() throws Exception {
        restTask.updateStatusTask(task);
        verify(taskService).updateTaskForStatus(task);
    }

    @Test
    public void getIdOfTask() throws Exception {
        when(session.getAttribute("taskId")).thenReturn(1);
        int resultId = restTask.getIdOfTask(session);
        verify(session).getAttribute("taskId");
        assertEquals(resultId, 1);
    }

    @Test
    public void getDescriptionOfTask() throws Exception {
        int taskId = 1;
        when(taskRepository.getOne(taskId)).thenReturn(task);
        when(task.getDescription()).thenReturn("testDescription");

        DescriptionDTO descriptionDTO = restTask.getDescriptionOfTask(taskId);
        verify(taskRepository).getOne(taskId);
        assertEquals(descriptionDTO.getDescription(), "testDescription");
    }

}