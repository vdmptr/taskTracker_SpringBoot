package vdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vdm.entity.User;
import vdm.helpers.Role;

import java.util.List;

public interface UserRepository extends JpaRepository<vdm.entity.User, Integer> {

    List<vdm.entity.User> findByNameAndLastName(String name, String lastName);

    List<vdm.entity.User> findUsersByRole(Role role);

    List<vdm.entity.User> findUsersByEmail(String email);
}
