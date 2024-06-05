package org.example.repository;

import org.example.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository  {
    Task add(Task task);
    Task getTaskById(Integer id);
    List<Task> getAllTask();
    void deleteTask(int id);
    Task update(Task task);


}
