package vdm.dto;

import vdm.helpers.Status;

public class TaskDTO {

    private int taskId;

    private String description;

    private Status status = Status.WAITING;

    private int developerId;

    private String developerName;

    private String developerLastName;


    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getDeveloperLastName() {
        return developerLastName;
    }

    public void setDeveloperLastName(String developerLastName) {
        this.developerLastName = developerLastName;
    }
}