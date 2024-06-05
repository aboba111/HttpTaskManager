package org.example;

import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;
import org.example.httpServer.TaskHttpServer;
import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.example.repository.TaskRepositoryImpl;
import org.example.service.HistoryService;
import org.example.service.HistoryServiceImpl;
import org.example.service.TaskService;
import org.example.service.TaskServiceImpl;

public class TaskManager {
    @SneakyThrows
    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        HistoryService historyService = new HistoryServiceImpl();
        TaskService taskService = new TaskServiceImpl(taskRepository, historyService);
        TaskHttpServer taskHttpServer = new TaskHttpServer(8081,taskService);
        taskHttpServer.start();
    }
}