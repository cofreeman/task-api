package com.nhnacademy.taskApi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModifyTaskDto {

    private String taskTitle;
    private String taskContent;
}
