package com.nhnacademy.taskApi.exception;

public class NotFoundProjectException extends IllegalArgumentException{

    public NotFoundProjectException() {
        super("could not found project");
    }
}
