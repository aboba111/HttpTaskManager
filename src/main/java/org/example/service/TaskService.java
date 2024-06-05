package org.example.service;

import org.example.model.Task;

import java.util.List;

public interface TaskService {
    Task add(Task task);

    Task getById(int id);

    List<Task> getAllTask();

    void deleteTask(int id);

    Task update(Task task);

    List<Task> getHistory();


}
