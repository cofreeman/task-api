package com.nhnacademy.taskApi.exception;

public class NotFoundMilestoneException extends IllegalArgumentException{
    public NotFoundMilestoneException() {
        super("could not found milestone");
    }
}
