package org.example.httpServer.handlers;

import com.sun.net.httpserver.HttpExchange;
import org.example.exeption.ErrorHandler;
import org.example.exeption.ErrorResponse;
import org.example.model.Task;
import org.example.service.TaskService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TaskHandler extends AbstractHandler {

    private final TaskService taskService;

    public TaskHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String query = exchange.getRequestURI().getQuery();
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        int statusCode=0;
        boolean isValidRequest = true;
        Object responseObject=null;
        try {
            switch (method) {
                case "GET":
                    if (query != null) {
                        responseObject = handleGetTaskRequest(query);
                    } else {
                        responseObject = handleGetTaskListRequest();

                    }
                    statusCode = 200;
                    break;
                case "POST":
                    responseObject = handlePostTaskRequest(body);
                    statusCode = 201;
                    break;
                case "PUT":
                    responseObject = handlePutRequest(body);
                    statusCode = 202;
                    break;
                case "DELETE":
                    responseObject = handleDeleteRequest(query);
                    statusCode = 204;
                    break;
                default:
                    isValidRequest = false;
            }
            sendResponse(exchange,statusCode,responseObject,isValidRequest);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            ErrorResponse errorResponse= errorHandler.handleException(e);
            responseObject = errorResponse;
            statusCode = errorResponse.getErrorCode();
        }


    }


    private Object handleGetTaskRequest(String query) {
        String strParam = getQueryParam(query, "id");
        int id = Integer.getInteger(strParam);
        return taskService.getById(id);
    }

    private Object handlePostTaskRequest(String body){
        Task task = gson.fromJson(body, Task.class);
        return taskService.add(task);
    }

    private Object handlePutRequest(String body){
        Task task = gson.fromJson(body, Task.class);
        return taskService.update(task);
    }
    private Object handleDeleteRequest(String query){
        String strParam = getQueryParam(query, "id");
        int id = Integer.getInteger(strParam);
        taskService.deleteTask(id);
        return "";
    }

    public Object handleGetTaskListRequest() {
        return taskService.getAllTask();
    }

    

}
