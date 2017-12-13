package vdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vdm.entity.Project;
import vdm.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
        List<Task> findTasksByProject(Project project);


}
