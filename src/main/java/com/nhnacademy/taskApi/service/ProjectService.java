package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.ProjectByUserIdRequestDto;
import com.nhnacademy.taskApi.dto.ProjectRequestDto;
import com.nhnacademy.taskApi.entity.Projects;

import java.util.List;

public interface ProjectService {

    Projects findById(Long id);

    List<Projects> findAllProjectByUserId(Long id);

    Projects createProject(Long userId,ProjectRequestDto projectRequestDto);

    Projects modifyProject(Long userId,Long projectId, ProjectRequestDto projectRequestDto);
}
