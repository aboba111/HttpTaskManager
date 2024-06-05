package org.example.repository;

import org.example.exeption.TaskNotExistException;
import org.example.model.Task;

import java.util.*;

public class TaskRepositoryImpl implements TaskRepository{
    private Map<Integer, Task> tasks = new HashMap<>();
    private int versionId=1;
    @Override
    public Task add(Task task) {
        task.setId(versionId++);
        tasks.put(task.getId(),task);
        return task;
    }

    @Override
    public Task getTaskById(Integer id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> getAllTask() {
        List<Task> listTask=new ArrayList<>(tasks.values());
        return listTask;
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    @Override
    public Task update(Task task) {
        int id = task.getId();
        tasks.put(id,task);
        return task;

    }

}
