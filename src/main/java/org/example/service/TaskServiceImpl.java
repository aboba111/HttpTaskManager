package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exeption.AlreadyExistException;
import org.example.exeption.TaskNotExistException;
import org.example.model.Task;
import org.example.repository.TaskRepository;

import java.util.*;

public class TaskServiceImpl implements TaskService {
    private HistoryService historyService;
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository, HistoryService historyService){
        this.taskRepository=taskRepository;
        this.historyService=historyService;
    }

    @Override
    public Task add(Task task) {
        if(taskRepository.getTaskById(task.getId())!=null){
            throw new AlreadyExistException("задача с данным id же существует");
        }
        return taskRepository.add(task);
    }

    @Override
    public Task getById(int id) {
        this.checkId(id);
        Task task=taskRepository.getTaskById(id);
        historyService.add(task);
        return task;

    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.getAllTask();
    }

    @Override
    public void deleteTask(int id) {
        this.checkId(id);
        taskRepository.deleteTask(id);
    }

    @Override
    public Task update(Task task) {
        int id = task.getId();
        this.checkId(id);
        return taskRepository.update(task);
    }

    @Override
    public List<Task> getHistory() {
        return historyService.getHistory();
    }

    private void checkId(int id){
        if(taskRepository.getTaskById(id)==null){
            throw new TaskNotExistException("id не существует");
        }
    }
}
