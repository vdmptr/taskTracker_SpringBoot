package vdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vdm.entity.Project;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findProjectByManagerId(int managerId);
}

