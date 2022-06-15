package com.nhnacademy.taskApi.exception;

public class NotFoundProjectMemberException extends IllegalArgumentException{

    public NotFoundProjectMemberException() {
        super("member not join in project");
    }
}
