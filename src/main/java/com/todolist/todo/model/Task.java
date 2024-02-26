package com.todolist.todo.model;

import java.util.Date;

public class Task {
    private int taskId;
    private String taskName;
    private String taskStatus;
    private Date dueDate;

    // Constructors
    public Task(int taskId, String taskName, String taskStatus, Date dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.dueDate = dueDate;
    }

    public Task() { }

    // Getters/setters
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // Overridden toString method
    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}