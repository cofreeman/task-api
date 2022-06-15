package com.nhnacademy.taskApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TaskCreateDto {

    private String taskTitle;
    private String taskContent;
}
