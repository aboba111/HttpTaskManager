package org.example.httpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.example.httpServer.handlers.HistoryHandler;
import org.example.httpServer.handlers.TaskHandler;
import org.example.model.Task;
import org.example.service.TaskService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class TaskHttpServer {
    private HttpServer server;
    private TaskService taskService;
    public TaskHttpServer(int port, TaskService taskService) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        this.taskService = taskService;
        mapHandlers();
    }
    private void mapHandlers(){
        server.createContext("/tasks/task/", new TaskHandler(taskService));
        server.createContext("/tasks/history/", new HistoryHandler(taskService));
    }
    public void start() {
        server.start();
    }
    public void stop() {
        server.stop(1000);
    }
}
