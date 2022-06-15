package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.ProjectMemberCreateDto;
import com.nhnacademy.taskApi.entity.ProjectMembers;

import java.util.List;

public interface ProjectMemberService {

    List<ProjectMembers> findAllProjectMembers(Long userId, Long projectId);

    ProjectMembers createProjectMember(Long userId, Long projectId, ProjectMemberCreateDto projectMemberCreateDto);
}
