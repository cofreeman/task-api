package com.nhnacademy.taskApi.exception;

public class NotProjectAdminException extends IllegalArgumentException {
    public NotProjectAdminException() {
        super("user is not admin");
    }
}
