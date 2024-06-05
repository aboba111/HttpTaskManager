package org.example.httpServer.handlers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Getter;
import lombok.Setter;
import org.example.adapter.LocalDateTypeAdapter;
import org.example.exeption.AlreadyExistException;
import org.example.exeption.ErrorHandler;
import org.example.exeption.ErrorResponse;
import org.example.exeption.TaskNotExistException;
import org.example.model.Task;
import org.example.service.TaskService;

import java.io.IOException;
import java.time.LocalDateTime;

public abstract class AbstractHandler implements HttpHandler {
    @Getter
    @Setter
    protected static ErrorHandler errorHandler;
    protected Gson gson;
    protected static TaskService taskService;

    public AbstractHandler(){
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter())
                .create();
    }

    static
    {
        errorHandler = new ErrorHandler();
        errorHandler.addNewErrorCase(TaskNotExistException.class,new ErrorResponse("Задача не найдена", 404));
        errorHandler.addNewErrorCase(AlreadyExistException.class, new ErrorResponse("Задача уже существует", 409));
    }

    protected String getQueryParam(String query, String paramName){
        int indexStart= query.indexOf(paramName) + paramName.length() + 1 ;
        int indexEnd= query.indexOf('&', indexStart);
        if(indexEnd == -1){
            indexEnd= query.length();
        }
        return query.substring(indexStart, indexEnd);
    }

    protected void sendResponse(HttpExchange exchange, int statusCode, Object body, boolean isValidRequest){
        if(!isValidRequest){
            statusCode = 404;
            body = "";
        }
        String strGson = gson.toJson(body);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        try {
            exchange.sendResponseHeaders(statusCode,strGson.getBytes().length);
            exchange.getResponseBody().write(strGson.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        exchange.close();
    }


}
