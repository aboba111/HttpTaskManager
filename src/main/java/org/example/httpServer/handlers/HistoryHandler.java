package org.example.httpServer.handlers;

import com.sun.net.httpserver.HttpExchange;
import org.example.exeption.ErrorResponse;
import org.example.service.HistoryService;
import org.example.service.TaskService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HistoryHandler extends AbstractHandler{

  TaskService taskService;

  public HistoryHandler(TaskService taskService) {
      this.taskService = taskService;
  }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        int statusCode=0;
        boolean isValidRequest = true;
        Object responseObject=null;
        try {
            switch (method) {
                case "GET":
                    responseObject = handleGetHistory();
                    break;
                default:
                    isValidRequest = false;
            }
            sendResponse(exchange,statusCode,responseObject,isValidRequest);
        }
        catch (Exception e){
            ErrorResponse errorResponse= errorHandler.handleException(e);
            responseObject = errorResponse;
            statusCode = errorResponse.getErrorCode();
        }
    }

    private Object handleGetHistory(){
        return taskService.getHistory();
    }
}
