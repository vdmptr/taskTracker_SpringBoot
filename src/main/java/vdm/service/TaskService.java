package vdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vdm.entity.Project;
import vdm.entity.Task;
import vdm.repository.ProjectRepository;
import vdm.repository.TaskRepository;
import vdm.repository.UserRepository;
import vdm.dto.TaskDTO;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskService {

    public TaskService() {
    }

    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    @Autowired
    TaskService(TaskRepository taskRepository,
                ProjectRepository projectRepository,
                UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<TaskDTO> getTasksOfProject(int projectId){
        Project project = projectRepository.getOne(projectId);
        List<Task> list = taskRepository.findTasksByProject(project);
        return list.stream()
                .map(this::convertTaskToTaskDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskDTO updateTaskForUser(int taskId, vdm.entity.User user){
        Task persistenceTask = taskRepository.getOne(taskId);
        vdm.entity.User persistenceUser = userRepository.getOne(user.getUserId());
        persistenceTask.setDeveloper(persistenceUser);
        persistenceTask = taskRepository.saveAndFlush(persistenceTask);
        return convertTaskToTaskDTO(persistenceTask);
    }

    @Transactional
    public TaskDTO updateTaskForStatus(Task statusTask){
        Task persistenceTask = taskRepository.getOne(statusTask.getTaskId());
        persistenceTask.setStatus(statusTask.getStatus());
        persistenceTask = taskRepository.saveAndFlush(persistenceTask);
        return convertTaskToTaskDTO(persistenceTask);
    }

    @Transactional
    public TaskDTO addTaskToProject(Task task, int projectId){
        Project persistenceProject = projectRepository.getOne(projectId);
        task.setProject(persistenceProject);
        Task persistenceTask = taskRepository.saveAndFlush(task);
        return convertTaskToTaskDTO(persistenceTask);
    }

    @Transactional
    public TaskDTO addTaskToUser(Task task, int userId){
        vdm.entity.User persistenceUser = userRepository.getOne(userId);
        Task persistenceTask = taskRepository.getOne(task.getTaskId());
        persistenceTask.setDeveloper(persistenceUser);
        persistenceTask = taskRepository.saveAndFlush(persistenceTask);
        return convertTaskToTaskDTO(persistenceTask);
    }

    private TaskDTO convertTaskToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setTaskId(task.getTaskId());
        Task persistenceTask = taskRepository.getOne(task.getTaskId());
        vdm.entity.User developer = persistenceTask.getDeveloper();
        if (developer != null){
            taskDTO.setDeveloperId(developer.getUserId());
            taskDTO.setDeveloperName(developer.getName());
            taskDTO.setDeveloperLastName(developer.getLastName());
        }
        return taskDTO;
    }
}
