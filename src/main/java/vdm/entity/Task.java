package vdm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import vdm.helpers.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.AUTO )
    private int taskId;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.WAITING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User developer;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public User getDeveloper() {
       return developer;
    }

    public void setDeveloper(User developer) {
       this.developer = developer;
   }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComments(List<Comment> comments) {
       this.comments = comments;
   }

    public Status getStatus() {
       return status;
   }

    public void setStatus(Status status) {
       this.status = status;
   }
}
