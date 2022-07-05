package com.nhnacademy.taskApi.service;


import com.nhnacademy.taskApi.dto.ProjectByUserIdRequestDto;
import com.nhnacademy.taskApi.dto.ProjectRequestDto;
import com.nhnacademy.taskApi.entity.ProjectMembers;
import com.nhnacademy.taskApi.entity.Projects;
import com.nhnacademy.taskApi.exception.NotFoundProjectException;
import com.nhnacademy.taskApi.repository.ProjectMembersRepository;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMembersRepository projectMembersRepository;

    @Override
    public Projects findById(Long id) {
        return projectRepository.findById(id).orElseThrow(NotFoundProjectException::new);
    }

    @Override
    public List<Projects> findAllProjectByUserId(Long userId) {
        return projectRepository.findAllProjectByUserId(userId);
    }


    // 만들면 참여프로젝트에 관리자 등록되고 프로젝트도 생성됨
    @Transactional
    @Override
    public Projects createProject(Long userId,ProjectRequestDto projectRequestDto) {

        Projects projects = new Projects(userId, projectRequestDto);
        Projects project = projectRepository.save(projects);

        ProjectMembers projectMembers = new ProjectMembers(project);
        projectMembersRepository.save(projectMembers);

        return project;
    }

    @Override
    public Projects modifyProject(Long userId,Long projectId, ProjectRequestDto projectRequestDto) {
        Projects projects = projectRepository.findById(projectId).orElseThrow(NotFoundProjectException::new);
        projects.setProjectName(projectRequestDto.getProjectName());
        projects.setUserId(userId);
        projects.setId(projectRequestDto.getId());

        return projectRepository.save(projects);
    }
}
