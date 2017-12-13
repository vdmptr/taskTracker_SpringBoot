package vdm.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import vdm.entity.Project;
import vdm.entity.Task;
import vdm.helpers.Status;
import vdm.repository.ProjectRepository;
import vdm.repository.TaskRepository;
import vdm.repository.UserRepository;
import vdm.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService = new TaskService();

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Task task;

    @Mock
    private vdm.entity.User user;

    @Mock
    private Project project;

    @Before
    public void setUp() throws Exception {
        int projectId = 1;
        int userId = 1;
        int taskId = 1;
        when(taskRepository.getOne(taskId)).thenReturn(task);
        when(userRepository.getOne(userId)).thenReturn(user);
        when(taskRepository.saveAndFlush(task)).thenReturn(task);
        when(projectRepository.getOne(projectId)).thenReturn(project);
        when(task.getDescription()).thenReturn("testDescription");
        when(task.getStatus()).thenReturn(Status.WAITING);
        when(task.getTaskId()).thenReturn(1);
        when(task.getDeveloper()).thenReturn(user);
        when(user.getUserId()).thenReturn(1);
        when(user.getName()).thenReturn("testName");
        when(user.getLastName()).thenReturn("testLastName");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskRepository.findTasksByProject(project))
                                                 .thenReturn(taskList);
    }

    @Test
    public void getTasksOfProject() throws Exception {
        int projectId = 1;

        List<TaskDTO> taskDTOList = taskService.getTasksOfProject(projectId);

        assertEquals(taskDTOList.get(0).getTaskId(),1);
    }

    @Test
    public void updateTaskForUser() throws Exception {
        int taskId = 1;

        TaskDTO taskDTO = taskService.updateTaskForUser(taskId, user);

        assertEquals(taskDTO.getTaskId(),1);
    }

    @Test
    public void updateTaskForStatus() throws Exception {

        TaskDTO taskDTO = taskService.updateTaskForStatus(task);

        assertEquals(taskDTO.getStatus(), Status.WAITING);
    }

    @Test
    public void addTaskToProject() throws Exception {
        int projectId = 1;

        TaskDTO taskDTO = taskService.addTaskToProject(task, projectId);

        assertEquals(taskDTO.getTaskId(), 1);
    }

    @Test
    public void addTaskToUser() throws Exception {
        int userId = 1;

        TaskDTO taskDTO = taskService.addTaskToUser(task, userId);

        assertEquals(taskDTO.getDeveloperId(), 1);
    }
}