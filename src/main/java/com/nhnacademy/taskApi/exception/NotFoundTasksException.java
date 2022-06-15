package com.nhnacademy.taskApi.exception;

public class NotFoundTasksException extends IllegalArgumentException {

    public NotFoundTasksException() {
        super("could not found task");
    }
}
