package org.example.exeption;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ErrorHandler{
    private final Map<Type,ErrorResponse> errorMap= new HashMap<>();

    public void addNewErrorCase(Type type, ErrorResponse errorResponse){
        errorMap.put(type,errorResponse);
    }

    public ErrorResponse  handleException(Exception e){
        if(errorMap.containsKey(e.getClass())){
            return errorMap.get(e.getClass());
        }
        ErrorResponse defaultResponse = new ErrorResponse("Server error", 500);
        return defaultResponse;
    }
}
