package org.example.service;

import org.example.model.Task;

import java.util.List;

public interface HistoryService {
    void add(Task task);
    void delete(int id);
    List<Task> getHistory();

}
