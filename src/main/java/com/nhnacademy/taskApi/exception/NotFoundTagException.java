package com.nhnacademy.taskApi.exception;

public class NotFoundTagException extends IllegalArgumentException{

    public NotFoundTagException() {
        super("could not found tag");
    }
}
