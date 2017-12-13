package vdm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;
import vdm.helpers.Role;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column(name = "id")
    private int userId;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;

    private String lastName;

    private String email;

    private String passwordHash;

    private boolean isEnabled;

    @OneToMany(mappedBy = "developer",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Task> taskList ;


    public List<Task> getTaskList() {
      return taskList;
  }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
