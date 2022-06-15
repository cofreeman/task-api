package com.nhnacademy.taskApi.exception;

public class NotFoundCommentException extends IllegalArgumentException{
    public NotFoundCommentException() {
        super("could not found comment");
    }
}
