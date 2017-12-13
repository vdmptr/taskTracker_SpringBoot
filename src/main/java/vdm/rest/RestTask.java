package vdm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vdm.entity.Task;
import vdm.entity.User;
import vdm.repository.TaskRepository;
import vdm.service.TaskService;
import vdm.dto.DescriptionDTO;
import vdm.dto.TaskDTO;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class RestTask {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @Autowired
    RestTask(TaskRepository taskRepository,
             TaskService taskService){
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @GetMapping(value = "/{projectId}",
                produces = "application/json")
    public List<TaskDTO> getAllTaskOfProject(@PathVariable int projectId){
        return taskService.getTasksOfProject(projectId);
    }

    @PostMapping(value = "/{projectId}",
                 produces = "application/json")
    public TaskDTO addTaskToProject(@PathVariable int projectId,
                                    @RequestBody Task task){
        return taskService.addTaskToProject(task, projectId);
    }

    @PostMapping(value = "/{projectId}/{developerId}",
            produces = "application/json")
    public TaskDTO addTaskOfDeveloper(@PathVariable int projectId,
                                      @PathVariable int developerId,
                                      @RequestBody Task task){
        taskService.addTaskToProject(task, projectId);
        return taskService.addTaskToUser(task, developerId);
    }

    @PutMapping(value = "/{taskId}",
                produces = "application/json")
    public TaskDTO updateTaskForUser(@PathVariable int taskId,
                                     @RequestBody User user){
        return taskService.updateTaskForUser(taskId, user);
    }

    @PutMapping(value = "/",
            produces = "application/json")
    public void updateStatusTask(@RequestBody Task task){
        taskService.updateTaskForStatus(task);
    }

    @GetMapping(value = "/init/id",
            produces = "application/json")
    public int getIdOfTask(HttpSession session){
        return (int)session.getAttribute("taskId");
    }

    @GetMapping(value = "/description/{taskId}",
            produces = "application/json")
    public DescriptionDTO getDescriptionOfTask(@PathVariable int taskId){
        Task task = taskRepository.getOne(taskId);
        DescriptionDTO descriptionDTO  = new DescriptionDTO();
        descriptionDTO.setDescription(task.getDescription());
        return descriptionDTO;
    }
}
