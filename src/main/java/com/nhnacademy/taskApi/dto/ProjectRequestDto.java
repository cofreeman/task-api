package com.nhnacademy.taskApi.dto;

import com.nhnacademy.taskApi.entity.ProjectState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectRequestDto {

    private String projectName;
    private String id;
}
