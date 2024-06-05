package org.example.exeption;

public class TaskNotExistException extends RuntimeException{
    public TaskNotExistException(String massage){
        super(massage);
    }
}
